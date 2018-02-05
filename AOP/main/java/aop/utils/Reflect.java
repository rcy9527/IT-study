package aop.utils;

import aop.annotation.After;
import aop.annotation.Before;
import aop.annotation.MyAspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 6:21
 * Description:
 **/
public class Reflect {

    Map<String,String> map;//存入的是方法名以及其注解
    Map<String,String> clazzMap;
    public Reflect() throws ClassNotFoundException{
        map=new HashMap<>();
        clazzMap =new HashMap<>();
        getAnnotationClass();
    }


    // 这里返回的是已经全部存好的map方面ProxyUtil使用
    public Map<String,String> getMap(){
        return map;
    }


    public void getAnnotationClass() throws ClassNotFoundException{
        String clazzName="aop.aspect.Player";
        /*
         * 通过多线程的类型加载加载该类
         */
        Class<?> clazz = Class.forName(clazzName,false,Thread.currentThread().getContextClassLoader());// 这里为了省事直接动态加载了该类
        /*判断该方法是否包含MyAnnotation2注解
         * 例如：@Targe ，@Retention等
         */
        if(clazz.isAnnotationPresent(MyAspect.class)){
            /* 
            1、getMethods返回一个包含某些 Method 对象的数组，
            这些对象反映此 Class 对象所表示的类或接口的公共 member 方法。

            2、getDeclaredMethods返回 Method 对象的一个数组，
            这些对象反映此 Class 对象表示的类或接口声明的所有方法，
            包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。

            也就是说getDeclaredMethods能拿到所有（不包括继承的方法），
            而getMethods只能拿到public方法（包括继承的类或接口的方法）

            还有只得注意的是这两个方法返回的数组中的元素的顺序是无序的，它和你在类中定义方法的顺序无关
             */
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods){
                //获取注解：before
                if(method.isAnnotationPresent(Before.class)){
                    //获取该方法的MyAnnotation注解实例:即获得befor()方法
                    //如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。
                    Before before = method.getAnnotation(Before.class);
                    //获得befoe注解中value的值
                    String beforeValue = before.value();
                    // 存入的是方法名和注解名以及执行的顺序
                    map.put(method.getName()+ "-"+clazzName+"-"+"before",beforeValue.substring(0,beforeValue.length()-2));
                    continue;
                }
                if (method.isAnnotationPresent(After.class)) {
                    After after =method.getAnnotation(After.class);
                    String afterValue=after.value();
                    map.put(method.getName()+ "-"+clazzName+"-"+"after",afterValue.substring(0,afterValue.length()-2));
                }

            }
        }
    }

}