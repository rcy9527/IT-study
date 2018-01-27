package study.small;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2017-12-17
 * @Time: 上午 1:25
 * Description:
 **/
public class Student {


    private String name;
    private Integer age;
    private Teacher teacher;


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }



    public Student (String name,Integer age){
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public Student(){
    }
}