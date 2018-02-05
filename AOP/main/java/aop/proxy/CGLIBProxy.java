package aop.proxy;

import aop.bean.ProxyEntity;
import aop.utils.ProxyUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 6:18
 * Description:
 **/
public class CGLIBProxy implements MethodInterceptor {
    private Object target;
    private ProxyUtil proxyUtil ;
    public CGLIBProxy(Object target) throws ClassNotFoundException {
        this.target = target;
        proxyUtil =new ProxyUtil();
    }

    public <T> T getProxy(){
        return (T) Enhancer.create(this.target.getClass(),this);
    }
    public <T> T getProxy(Class<?> clazz){
        return (T) Enhancer.create(this.target.getClass(),this);
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        ProxyEntity proxyEntity =new ProxyEntity(proxy,this.target.getClass(),obj,method,args);
        return proxyUtil.generateEntity(proxyEntity);
    }

}