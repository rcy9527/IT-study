package xiancheng;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-01-08
 * @Time: 上午 10:30
 * Description:
 **/
public class MyRunnable implements Runnable {
    private static int num = 0;
    private String name;
    public MyRunnable(String name){
        num ++;
        this.name = name;
    }

    @Override
    public void run(){
        System.out.println("主动创建的第"+num+"个线程");
        System.out.println( "name"+name + "子线程ID：" +Thread.currentThread().getId());
    }
}