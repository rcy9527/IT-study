package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 3:42
 * Description: JDK动态代理
 **/
public class CountProxyJDK implements InvocationHandler{

    private Object target;


    /**
     * @description:  绑定委托对象并返回一个代理类
     * @param target
     * @return: java.lang.Object
     * @author: Administrator
     * @date: 2018-02-05  下午 4:18
     */

    public Object bind(Object target){
        this.target = target;
        //启动类加载器，加载当前的类
        ClassLoader classLoader = target.getClass().getClassLoader();
        //得到该类的全部接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //得到InvocationHandler接口的子类实例 ，即invoke
        InvocationHandler h = this;
        //获取代理对象
        return Proxy.newProxyInstance(classLoader,interfaces,h);
    }


    /** 
     * @description:  调用方法
     * @param
     * @return:   
     * @author: Administrator  
     * @date: 2018-02-05  下午 4:19  
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws  Throwable{


        Object result = null;
        System.out.println("事物开始");
        //执行方法
        result=method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }


}