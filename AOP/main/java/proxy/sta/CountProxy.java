package proxy.sta;

import proxy.Count;
import proxy.CountImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-02-05
 * @Time: 下午 3:21
 * Description:
 **/
public class CountProxy implements Count {

    private CountImpl countImpl;


    public CountProxy(CountImpl countImpl){
        this.countImpl = countImpl;
    }

    //代理方法一
    @Override
    public void queryCount(){
        System.out.println("事务处理之前");
        // 调用委托类的方法;
        countImpl.queryCount();
        System.out.println("事务处理之后");
    }


    @Override
    public void updateCount(){
        System.out.println("事务处理之前");
        countImpl.updateCount();
        System.out.println("事务处理之后");
    }






}