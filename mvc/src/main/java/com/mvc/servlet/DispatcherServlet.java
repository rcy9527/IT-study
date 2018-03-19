package com.mvc.servlet;

import com.mvc.annotation.*;
import com.mvc.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-03-16
 * @Time: 下午 3:18
 * Description: 前端控制器
 **/
public class DispatcherServlet extends HttpServlet {


    private static Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    //存储注解@Controller 和 @Service 的类的全限定类名
    private List<String> beanNames = new ArrayList<>();

    //存储注解@Controller 和 @Service 的类的实例
    private Map<String,Object> instanceMaps = new HashMap<>();

    private Map<String,Method> handlerMaps = new HashMap<>();

    private Map<String,Object> controllerMaps = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException{
        try{
            //通过web.xml 获取基本信息
            String mvcConfig = config.getInitParameter("contextConfigLocstion").replaceFirst("\\*","").replaceFirst("\\bclasspath:\\b","");
            log.info(mvcConfig);
            //获取需要进行扫描的路径
            String basePackName = CommonUtils.getBasePackName(mvcConfig);
            log.info("扫描的基包是：" + basePackName);
            //扫描指定目录下的com.mvc
            scanPack(basePackName);
            //3.通过注解对象，找到每个bean，反射new出实例
            reflectBeansInstance();
            //将反射出的实例注入变量
            doIoc();
            // 5.handlerMapping通过基部署 和 基于类的url找到相应的处理器
            initHandlerMapping();



        } catch (Exception e){
            e.printStackTrace();
            if (e instanceof ServletException){
                new ServletException(e);
            }
        }
    }




    /**
     * @description: 获取指定路径下的每一个文件的详细路径
     * @param basePackName
     * @return: void
     * @author: Administrator
     * @date: 2018-03-18  下午 2:20
     */

    private void scanPack(String basePackName) throws Exception {
        //设置文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + CommonUtils.transferQualifiedToPath(basePackName));
        log.info("FileUrl : " + url);
        //读取文件
        File dir = new File(url.getFile());
        File[] files = dir.listFiles();
        for(File file : files){
            //当该文件是个文件夹的时候
            if(file.isDirectory()){
                scanPack(basePackName + "." + file.getName());
            }
            else if(file.isFile()){
                //把类的全限定类名写入beanNames
                beanNames.add(basePackName + "." + file.getName().replace(".class",""));
                log.info("扫描到的类有：" + basePackName + "." + file.getName().replace(".class", ""));
            }
        }
    }



    /**
     * @description:  把注解的类实例化。
     * @param
     * @return: void
     * @author: Administrator
     * @date: 2018-03-18  下午 4:33
     */

    private void reflectBeansInstance() throws Exception{
        if(beanNames.isEmpty()){
            return;
        }
        for (String className : beanNames){
            Class<?> aClass = Class.forName(className);

            if(aClass.isAnnotationPresent(Controller.class)){
                //实例化@Controller 的类
                Object controllerInstance = aClass.newInstance();
                //获得该类里@Controller 注解的信息
                Controller controllerAnnotation = aClass.getAnnotation(Controller.class);
                //获得@Controller 的value值，如果没有的话，默认使用类名 首字母小写
                String insMapKey = controllerAnnotation.value();
                if("".equals(insMapKey)){
                    insMapKey = CommonUtils.toLowerFirstWord(aClass.getSimpleName());
                }
                instanceMaps.put(insMapKey,controllerInstance);
            }
            else if(aClass.isAnnotationPresent(Service.class)){
                Object serviceInstance = aClass.newInstance();
                Service serviceAnnotation = aClass.getAnnotation(Service.class);
                String insMapKey = serviceAnnotation.value();
                if("".equals(insMapKey)){
                    insMapKey = CommonUtils.toLowerFirstWord(aClass.getSimpleName());
                }
                instanceMaps.put(insMapKey,serviceInstance);
            }
        }
    }



    /**
     * @description:  bean进行注入变量里
     * @param
     * @return: void
     * @author: Administrator
     * @date: 2018-03-19  下午 2:29
     */

    private void doIoc() throws Exception{
        if(instanceMaps.isEmpty()){
            throw new Exception("没有发现可注入的实例");
        }
        for(Map.Entry<String,Object> entry : instanceMaps.entrySet()){
            //获取该实例里所有类成员变量（无视访问限定符）
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            //遍历所有的类成员变量，获取有@Annotation 标记的类成员变量
            for(Field field : fields){
                //判断该类成员是否有注解
                if(field.isAnnotationPresent(Autowired.class)){
                    String insMapKey = field.getAnnotation(Autowired.class).value();
                    if("".equals(insMapKey)){
                        insMapKey = CommonUtils.toLowerFirstWord(field.getType().getSimpleName());
                    }
                    //更改私有变量的时候给与权限
                    field.setAccessible(true);
                    //把实例化的bean 注入变量里
                    field.set(entry.getValue(),instanceMaps.get(insMapKey));
                }
            }
        }
    }


    private void initHandlerMapping() throws Exception{
        if(instanceMaps.isEmpty()){
            throw new Exception("没有发现handler对象");
        }
        for(Map.Entry<String,Object> entry : instanceMaps.entrySet()){
            Class<?> aClass = entry.getValue().getClass();
            if(aClass.isAnnotationPresent(Controller.class)){
                String classURI = "";
                //允许类上的controller没有路径，路径写在类上的注解@RequestMapping
                if(aClass.isAnnotationPresent(RequestMapping.class)){
                    classURI = aClass.getAnnotation(RequestMapping.class).value();
                }
                else {
                    classURI = aClass.getAnnotation(Controller.class).value();
                }
                //遍历每个controller 的方法，找到被@RequestMapping 标记的方法，获得value
                Method[] methods = aClass.getMethods();
                for(Method method : methods){
                    if(method.isAnnotationPresent(RequestMapping.class)){
                        String methodURI = method.getAnnotation(RequestMapping.class).value();

                        //存入handlerMaps
                        String url = ("/" + classURI + "/" + methodURI ).replaceAll("/+","/");
                        log.info("controller url is : " + url);
                        //把url存入handlerMaps
                        handlerMaps.put(url,method);
                        //把controller的uri存入controllerMaps
                        controllerMaps.put(url,entry.getValue());
                    }
                }
            }
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doDispatch(req,resp);
    }




    /**
     * @description: 对请求进行处理
     * @param req
     * @param resp
     * @return: void
     * @author: Administrator
     * @date: 2018-03-19  下午 8:20
     */

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        if (handlerMaps.isEmpty()){
            return;
        }
        String uri = req.getRequestURI();//获取url路径（不包括域名和之前的）
        String contextPath = req.getContextPath();//获得项目名
        String url = uri.replaceFirst("\\b" + contextPath + "\\b","").replaceAll("/+","/");
        log.info("web url is : " + url);

        //获取执行请求方法
        Method handlerMethod = handlerMaps.get(url);
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(handlerMethod == null){
            out.print("404！！！您访问的资源不存在");
            return;
        }

        //获取方法的参数列表
        Parameter[] methodParameters = handlerMethod.getParameters();
        //获取方法需要的实参
        Object[] paramValues = new Object[methodParameters.length];
        for(int i = 0 ;i < methodParameters.length ; i++ ){
            //判断参数是否为 HttpServletRequest 或 HttpServletResponse,并把参数赋给paramValues
            if(ServletRequest.class.isAssignableFrom( methodParameters[i].getType() )){
                paramValues[i] = req;
            } else if(ServletResponse.class.isAssignableFrom( methodParameters[i].getType() )){
                paramValues[i] = resp;
            } else {
                //其他类型参数的获取String , Integer ,Float,Double
                //先获取参数名
                String bindingValue = methodParameters[i].getName();
                if(methodParameters[i].isAnnotationPresent(RequestParam.class)){
                    bindingValue = methodParameters[i].getAnnotation(RequestParam.class).value();
                }
                //获取实参
                String paramValue = req.getParameter(bindingValue);
                paramValues[i] = paramValue;
                //转换参数类型
                if(paramValue != null){
                    paramValues[i] = CommonUtils.parameterTypeConversion(methodParameters[i],paramValue);
                }
            }
        }

        try {
            handlerMethod.invoke(controllerMaps.get(url),paramValues);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}