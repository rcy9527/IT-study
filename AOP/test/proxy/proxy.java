package proxy;

import proxy.cglib.CountCglib;
import proxy.jdk.CountProxyJDK;
import proxy.sta.CountProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 3:31
 * Description:
 **/
@RunWith(JUnit4.class)
public class proxy {



    @Test
    public void countTest(){
        CountProxy countProxy = new CountProxy(new CountImpl());
        countProxy.queryCount();
        countProxy.updateCount();
    }




    @Test
    public void countJDK(){
        Long begin = System.currentTimeMillis();
        for(int i = 0;i<100 ;i++) {
            CountProxyJDK proxy = new CountProxyJDK();
            Count count = (Count) proxy.bind(new CountImpl());
            count.queryCount();
            count.updateCount();
        }
        System.out.println(System.currentTimeMillis() - begin);
    }



    @Test
    public void countCglib(){
        Long begin = System.currentTimeMillis();
        for(int i = 0;i<100 ;i++) {
        CountCglib cglib = new CountCglib();
        CountImpl countImpl = (CountImpl) cglib.getInstance(new CountImpl()) ;
        countImpl.queryCount();
        countImpl.updateCount();
        }
        System.out.println(System.currentTimeMillis() - begin);
    }




}