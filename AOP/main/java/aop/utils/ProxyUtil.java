package aop.utils;

import aop.bean.ProxyEntity;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 7:00
 * Description:
 **/
public class ProxyUtil {

    Reflect reflect;

    public ProxyUtil() throws ClassNotFoundException {
        reflect = new Reflect();
    }

    public void getMethod(String name) {
        Map<String, String> map = new HashMap<>();

    }

    //该方法负责代理
    public Object generateEntity(ProxyEntity proxyEntity) throws Throwable {
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, proxyEntity.getMethod().toString().indexOf("("));
        System.out.println("proxyEntity.getMethod():     " + proxyEntity.getMethod().toString());
        //获得存入map的前置与后置的执行方法信息
        Map<String, String> methodMap = reflect.getMap();
        for (Map.Entry<String, String> map : methodMap.entrySet()) {
            if (map.getValue().equals(proxyMethodValue)) {
                //获得织入方法的具体信息到数组
                String[] str = mapKeyDivision(map.getKey());
                if ("before".equals(str[2])) {
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader()); // 多线程加载该类
                    Method method = clazz.getDeclaredMethod(str[0]);
                    method.invoke(clazz.newInstance(), null); // 反射调用方法

                }

            }
        }
        return doAfter(proxyEntity,methodMap);
    }

    private Object  doAfter(ProxyEntity proxyEntity,Map<String,String> map) throws Throwable {

        //调用sing()方法
        Object object = proxyEntity.getMethodProxy().invokeSuper(proxyEntity.getObject(), proxyEntity.getArgs());  // 调用方法
        String proxyMethodValue = proxyEntity.getMethod().toString().substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1, proxyEntity.getMethod().toString().indexOf("("));
        for(Map.Entry<String,String> aMap:map.entrySet()){
            if (aMap.getValue().equals(proxyMethodValue)){
                String[] str =mapKeyDivision(aMap.getKey());
                if("after".equals(str[2])){
                    Class<?> clazz = Class.forName(str[1], false, Thread.currentThread().getContextClassLoader()); // 加载该类
                    Method method = clazz.getDeclaredMethod(str[0]);
                    method.invoke(clazz.newInstance(), null); // 这一步需要原始的类
                }
            }
        }
        return object;
    }






    //分解map里面的键，因为里面存入了方法和类名以及执行顺序
    private String[] mapKeyDivision(String value) {
        String[] str = new String[3];
        str[0] = value.substring(0, value.indexOf("-"));  //注解下面的方法
        str[1] = value.substring(value.indexOf("-") + 1, value.lastIndexOf("-")); //注解所在的类
        str[2]=value.substring(value.lastIndexOf("-")+1,value.length()); //是before还是after
        return str;
    }


}