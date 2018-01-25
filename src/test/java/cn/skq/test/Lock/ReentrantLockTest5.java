package cn.skq.test.Lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *ReentrantLock lock = new ReentrantLock();当lock声明位置为成员变量时
        lock.lockInterruptibly()
        响应中断

 output:
 当前线程A获取了锁。。。
 当前线程B获取锁失败。。。
 当前线程A释放了锁。。
 */
public class ReentrantLockTest5 {

    private ReentrantLock lock = new ReentrantLock();//lock声明位置为成员变量

    public static void main(String[] args) {

        ReentrantLockTest5 test = new ReentrantLockTest5();
        MyThread2 a = new MyThread2(test, "A");
        MyThread2 b = new MyThread2(test, "B");
        a.start();
        b.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.interrupt();

    }

    public void insert(Thread thread) throws InterruptedException {

        lock.lockInterruptibly();

        try {
            System.out.println("当前线程"+thread.getName()+"获取了锁。。。");
            long now = System.currentTimeMillis();
            for ( ;  ; ){//耗时操作
                if (System.currentTimeMillis()-now> Integer.MAX_VALUE) break;
            }
            ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
            rrwl.readLock();
        }finally {
            lock.unlock();
            System.out.println("当前线程"+thread.getName()+"释放了锁。。。");
        }
        System.out.println("this is over");
    }
}

class MyThread2 extends Thread{

    private ReentrantLockTest5 test;

    public MyThread2(ReentrantLockTest5 test,String name){
        super(name);
        this.test = test;
    }

    public void run(){
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println("time="+ System.currentTimeMillis()+"当前线程："+Thread.currentThread().getName()+"被中断。。。。");
        }
    }

}
