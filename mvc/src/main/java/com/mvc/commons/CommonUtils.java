package com.mvc.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-03-18
 * @Time: 上午 10:55
 * Description:
 **/
public class CommonUtils {


    private static Logger log = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * @description:  把properties 中scanPackage的value取出
     * @param mvcConfig
     * @return: java.lang.String
     * @author: Administrator
     * @date: 2018-03-18  上午 11:38
     */

    public static String getBasePackName(String mvcConfig)  {

        String basePackName = null;
        Properties properties = new Properties();
        //把web.xml中的contextConfigLocation 对应 value值得文件加载的流里面
        InputStream resourceAsStream = CommonUtils.class.getClassLoader().getResourceAsStream(mvcConfig);
        try {
            //用properties 文件加载文件里的内容
            properties.load(resourceAsStream);
            basePackName = properties.getProperty("scanPackage");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(resourceAsStream!=null){
                try {
                    //关闭资源
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return basePackName;
    }


    /**
     * @description:  把com.ycy 转化为文件路径 com/ycy
     * @param basePackName
     * @return: java.lang.String
     * @author: Administrator
     * @date: 2018-03-18  上午 11:44
     */

    public static String transferQualifiedToPath(String  basePackName) throws Exception {
        if(basePackName == null || basePackName.trim().isEmpty()){
            throw new Exception("扫描路径为空");
        }
        return basePackName.replace(".","/");
    }


    /**
     * @description:  转换第一个字母为小写
     * @param simpleName
     * @return: java.lang.String
     * @author: Administrator
     * @date: 2018-03-18  下午 4:19
     */
    public static String toLowerFirstWord(String simpleName){
        char[] charArray = simpleName.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }



    /**
     * @description:
     * @param methodParameter
     * @param paramValue
     * @return: java.lang.Object
     * @author: Administrator
     * @date: 2018-03-19  下午 8:06
     */

    public static Object parameterTypeConversion(Parameter methodParameter,String paramValue){
        if(Integer.class.isAssignableFrom(methodParameter.getType())){
            return Integer.parseInt(paramValue);
        } else if(Float.class.isAssignableFrom(methodParameter.getType()) ){
            return Float.parseFloat(paramValue);
        } else if(Double.class.isAssignableFrom(methodParameter.getType())){
            return Double.parseDouble(paramValue);
        } else if(Long.class.isAssignableFrom(methodParameter.getType())){
            return Long.parseLong(paramValue);
        }
        return paramValue;
    }


}