package study.small;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 下午 5:23
 * Description:
 **/
public class Teacher {



    private String name;
    private Integer clz;

    public Teacher(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClz() {
        return clz;
    }

    public void setClz(Integer clz) {
        this.clz = clz;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}