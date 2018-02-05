package aop.aspect;

import aop.annotation.After;
import aop.annotation.Before;
import aop.annotation.MyAspect;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 6:14
 * Description:
 **/
@MyAspect// 表明这是一个切点类
public class Player {

    @Before("aop.impl.Music.sing()")// 前置通知，当调用sing方法被调用的时候该方法会被在它之前调用
    public void before(){
        System.out.println("开始唱歌前的准备");
    }



    @After("aop.impl.Music.sing()") // 同理，在调用sing方法之后再来调用该方法
    public void after(){
        System.out.println("唱完之后开始评分");
    }
}