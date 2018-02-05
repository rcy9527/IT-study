package proxy;

import aop.impl.Music;
import aop.proxy.CGLIBProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 7:50
 * Description:
 **/
@RunWith(JUnit4.class)
public class aop {

    @Test
    public void aop() throws ClassNotFoundException {
        Music music = new Music();
        CGLIBProxy cglibProxy = new CGLIBProxy(music);
        ((Music)cglibProxy.getProxy()).sing("测试的人 ");

    }
}