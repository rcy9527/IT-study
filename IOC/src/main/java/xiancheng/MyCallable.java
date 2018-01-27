package xiancheng;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-01-09
 * @Time: 上午 1:21
 * Description:
 **/
public class MyCallable implements Callable<Object> {

    private String taskNum;

    public MyCallable(String taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception{
        System.out.println(">>>" + taskNum + "任务启动");
        Long begin = System.currentTimeMillis();
        Thread.sleep(1000);
        Long end = System.currentTimeMillis();
        Long time = end - begin;
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}