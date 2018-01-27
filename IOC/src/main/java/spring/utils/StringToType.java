package spring.utils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 下午 5:04
 * Description:
 **/
public class StringToType {

    public  static  Object StringToType(String type,String value){

        Object o = null;

        if(type.equals("class java.lang.String"))
        {
            o = value.toString();
        }
        else if(type.equals("class java.lang.Integer"))
        {
            o = Integer.valueOf(value);
        }

        else if(type.equals("class java.lang.Long"))
        {
            o = Long.valueOf(value);
        }

        return o;
    }

}