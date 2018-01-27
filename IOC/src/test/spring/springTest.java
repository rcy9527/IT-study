import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import spring.core.BeanFactory;
import spring.core.ClassPathXmlApplicationContext;
import study.small.Student;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-01-27
 * @Time: 下午 3:59
 * Description:
 **/
@RunWith(JUnit4.class)
public class springTest {


    @Test
    public void testSpring(){
        BeanFactory bf = new ClassPathXmlApplicationContext("/ApplicationContext.xml");
        Student student = (Student) bf.getBean("student1");
        System.out.println(student);

    }
}