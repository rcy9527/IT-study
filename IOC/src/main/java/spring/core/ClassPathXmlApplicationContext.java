package spring.core;

import spring.config.Bean;
import spring.config.Property;
import spring.config.XmlConfig;
import spring.utils.BeanUtil;
import spring.utils.StringToType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 根据配置文件加载bean 和生成容器
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 下午 2:02
 * Description:
 **/
public class ClassPathXmlApplicationContext implements  BeanFactory {

    //定义一个IOC容器
    private Map<String, Object> ioc;


    private Map<String, Bean> config;

    /**
     * @description:  1. 初始化IOC容器
     *                2. 加载配置文件，生成bean对象放入IOC容器
     * @param path
     * @return:
     * @author:
     * @date:
     */

    public ClassPathXmlApplicationContext(String path){
        //初始化容器
        ioc = new HashMap<String, Object>();
        //读取配置文件
        config = XmlConfig.getConfig(path);
        if(config!=null){
            //entrySet 是 键-值 对的集合，Set里面的类型是Map.Entry
            for(Map.Entry<String,Bean> entry: config.entrySet()){
                String beanId = entry.getKey();
                Bean bean = entry.getValue();
                //把已将生成的bean 放入map里
                ioc.put(beanId,createBean(bean));
            }
        }
    }


    /**
     * @description:  根据bean 生成对象实例
     * @param bean
     * @return: java.lang.Object
     * @author: Administrator
     * @date: 2018-01-27  下午 2:43
     */

    private Object createBean(Bean bean){
        Class clz = null;
        Object object = null;


        //根据 bean.getId()和getClassName()  实例化bean中载入的类
        try {
            //根据bean的class属性生成对象
            clz = Class.forName(bean.getClassName());

        }catch (ClassNotFoundException e){
            throw new RuntimeException("您配置的class属性不合法："+bean.getClassName());
        }

        try{
            //通过反射创建新的类, 如同 关键字new  但只能够调用无参的构造函数，即默认的构造函数；
            //实例化的是bean节点里class指向的类
            object = clz.newInstance();
        }catch (Exception e){
            throw new RuntimeException("该类缺少一个无参构造方法："+bean.getClassName());
        }

        //把生成bean里的List<propert>每个值  给予真正的生成的类（bean节点class的类）
        if(bean.getProperties()!=null){
            for(Property property : bean.getProperties()){
                //配置文件中使用的是value属性注入
                if(property.getValue() !=null){
                    //获取属性(类成员变量)对应的setter方法
                    Method method = BeanUtil.getSetterMethod(object,property.getName());
                    try {
                        //invoke（调用）就是调用Method类代表的方法。它可以让你实现动态调用，可以动态的传入参数
                        Object value = StringToType.StringToType(method.getParameterTypes()[0].toString(),property.getValue());
                        method.invoke(object, value);
                    }catch (Exception e){
                        throw new RuntimeException("属性名称不合法或者没有相应的setter方法："+property.getName());
                    }
                }

                //配置文件中使用的是ref属性注入
                if(property.getRef()!=null){
                    //获取属性对应的setter方法
                    Method getMethod = BeanUtil.getSetterMethod(object,property.getName());
                    //从容器中找到依赖的对象
                    Object obj = ioc.get(property.getRef());
                    if(obj == null){
                        throw new RuntimeException("没有找到依赖的对象："+property.getRef());
                    }else{
                        //调用set方法注入
                        try {
                            getMethod.invoke(object, obj);
                        } catch (Exception e) {
                            throw new RuntimeException("属性名称不合法或者没有相应的getter方法："+property.getName());
                        }
                    }
                }
            }
        }
        return object;
    }


    @Override
    public Object getBean(String beanName){
        return ioc.get(beanName);
    }

}