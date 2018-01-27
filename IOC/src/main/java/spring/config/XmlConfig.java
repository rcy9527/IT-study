package spring.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取xml文件
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 上午 11:30
 * Description:
 **/
public class XmlConfig {


    /**
     * @description: 读取path路径的xml文件信息
     * @param path
     * @return: java.util.Map<java.lang.String,spring.config.Bean>
     * @author: Administrator
     * @date: 2018-01-27  上午 11:40
     */

    public static Map<String,Bean> getConfig(String path){
        Map<String, Bean> configMap = new HashMap<String, Bean>();

        //使用dom4j和xpath读取xml文件，先读取，在解析， 在付给 Document
        //Document 对象代表整个 XML 文档。
        Document documented = null;
        //解析xml文件
        SAXReader reader = new SAXReader();
        //从classpath下的path路径获取文件
        InputStream in = XmlConfig.class.getResourceAsStream(path);
        try{
            documented = reader.read(in);
        }catch (DocumentException e){
            e.printStackTrace();
            throw new RuntimeException("请检查您的xml配置文件路径是否正确！");
        }
        //定义xpath，取出所有的bean
        String xpath = "//bean";
        //从任意位置的节点上选择名称为 item 的节点。 对bean进行遍历
        //Element 对象表示 XML 文档中的元素。从读取的信息里提取名为bean的节点信息
        List<Element> beanList = documented.selectNodes(xpath);
        if(beanList!=null){
            //把bean的信息提取放到  configMap  里边
            for(Element beanEle : beanList){
                Bean bean = new Bean();

                //检擦bean 的id是否有重复
                String id = beanEle.attributeValue("id");
                if(configMap.containsKey(id)){
                    throw new RuntimeException("bean节点ID重复：" + id);
                }
                //获取bean里id和class
                bean.setId(id);
                bean.setClassName(beanEle.attributeValue("class"));
                //获取bean节点下所有的property节点, elements 访问指定名称的所有节点
                List<Element> propertyList = beanEle.elements("property");
                if(propertyList!=null) {
                    for(Element property : propertyList){
                        Property prop = new Property();
                        prop.setName(property.attributeValue("name"));
                        prop.setValue(property.attributeValue("value"));
                        prop.setRef(property.attributeValue("ref"));

                        bean.getProperties().add(prop);
                    }
                }

                //把bean放入 configMap
                configMap.put(id,bean);
            }

        }
        return configMap;
    }

}