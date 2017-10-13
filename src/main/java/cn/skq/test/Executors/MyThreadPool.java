package cn.skq.test.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
    ArrayBlockingQueue 为有界阻塞队列
    LinkedBlockingQueue 为无界阻塞队列

    ThreadPoolExecutor(int corePoolSize, 核心线程数
                      int maximumPoolSize, 最大线程数（包含核心线程数）
                      long keepAliveTime, 线程最大存活时间
                      TimeUnit unit, 线程存活时间单位
                      BlockingQueue<Runnable> workQueue  阻塞队列
    创建自定义线程池时
        当提交的任务 < 核心线程数 , 任务直接被核心线程执行
        当提交的任务 > 核心线程数 , < 阻塞队列size ,核心线程被全部占用执行，余下任务在阻塞队列等待
        当提交的任务 > 核心线程数 , > 阻塞队列size ,核心线程被全部占用执行,阻塞队列占满，还有剩余任务
              当剩余任务 < maximumPoolSize - corePoolSize , 则在核心线程数基础上额外新创建线程运行这些任务
              当剩余任务 > maximumPoolSize - corePoolSize , 则采用拒绝策略拒掉多余的任务

    当然，当阻塞队列采用的是LinkedBlockingQueue(无界阻塞队列时),则不用考虑上述情况，当提交的任务> 核心线程数时，将
    一直添加到阻塞队列中等待，任务持续增加，直到内存溢出

 */
public class MyThreadPool {

    public static void main(String[] args) {
//        ArrayBlockingQueue<Runnable> quene = new ArrayBlockingQueue<Runnable>(2);
        final LinkedBlockingQueue<Runnable> quene = new LinkedBlockingQueue<Runnable>();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 3, 0, TimeUnit.SECONDS, quene);

        MyTask t1 = new MyTask(1,"任务1");
        MyTask t2 = new MyTask(2,"任务2");
        MyTask t3 = new MyTask(3,"任务3");
        MyTask t4 = new MyTask(4,"任务4");
        MyTask t5 = new MyTask(5,"任务5");

        poolExecutor.execute(t1);
        poolExecutor.execute(t2);
        poolExecutor.execute(t3);
        poolExecutor.execute(t4);
        poolExecutor.execute(t5);
    }
}
