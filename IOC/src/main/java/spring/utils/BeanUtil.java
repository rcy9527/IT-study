package spring.utils;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 下午 3:16
 * Description:
 **/
public class BeanUtil {


    /**
     * @description:  获取obj类的name属性的setter方法
     * @param object bean节点中class指向的对象
     * @param name	 要执行set方法的属性
     * @return: java.lang.reflect.Method
     * @author: Administrator
     * @date: 2018-01-27  下午 3:18
     */

    public static Method getSetterMethod(Object object,String name){
        Method method = null;
        //setter方法的名称,默认驼峰命名法
        // name.substring(0,1).toUpperCase()  获取该字符串的第一个字符并转化为大写
        name = "set" + name.substring(0,1).toUpperCase()+name.substring(1);
        try{
            //getMethod()获取的是类的所有共有方法
            Method[] methods = object.getClass().getMethods();
            //遍历该类所有的方法，找到  'name'  的set方法
            for(int i = 0;i< methods.length ; i++){
                Method m = methods[i];
                if(m.getName().equals(name)){

                    /* 获得对象所声明的公开方法
                     * name是得方法的名字
                     * parameterTypes是按声明顺序标识该方法形参类型。
                     */
                    method = object.getClass().getMethod(name,m.getParameterTypes());
                    break;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}