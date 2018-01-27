package spring.core;



/**
 * Created by Administrator on 2018-01-27.
 * 获取配置好的bean 接口
 */
public interface BeanFactory {

    public Object getBean(String beanName);
}
