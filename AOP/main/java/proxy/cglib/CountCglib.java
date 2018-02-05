package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 5:24
 * Description:
 **/
public class CountCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        //Enhancer允许为非接口类型创建一个Java代理。Enhancer动态创建了给定类型的子类但是拦截了所有的方法。
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }


    @Override
    /** 回调方法
        Object为由CGLib动态生成的代理类实例，
        Method为上文中实体类所调用的被代理的方法引用，
        Object[]为参数值列表，
        MethodProxy为生成的代理类对方法的代理引用。
     */
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("事物开始");
        proxy.invokeSuper(obj, args);
        System.out.println("事物结束");
        return null;
    }

}