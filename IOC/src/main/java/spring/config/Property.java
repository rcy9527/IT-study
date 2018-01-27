package spring.config;

/**
 * 封装bean里的Property
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 上午 11:29
 * Description:
 **/
public class Property {

    //要注入类的类成员变量名
    private String name;

    //要注入类的类成员变量的值
    private String value;
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}