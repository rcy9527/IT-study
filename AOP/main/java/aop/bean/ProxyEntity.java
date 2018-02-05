package aop.bean;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 7:01
 * Description:
 **/
public class ProxyEntity {

    private final MethodProxy methodProxy;
    private final Class<?> clazz;
    private final Object object;
    private final Method method;
    private final Object[] args;

    public ProxyEntity(MethodProxy methodProxy, Class<?> clazz, Object object, Method method, Object[] args) {
        this.methodProxy = methodProxy;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
        this.args = args;
    }



    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}