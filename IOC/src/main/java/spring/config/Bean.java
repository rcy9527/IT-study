package spring.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装xml的bean
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 上午 11:28
 * Description:
 **/
public class Bean {


    private String id;

    //存储要注入类的路径
    private String className;

    //放入注入类的属性的值
    private List<Property> properties = new ArrayList<Property>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);

    }

}