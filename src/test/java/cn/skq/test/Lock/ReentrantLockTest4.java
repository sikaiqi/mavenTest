package cn.skq.test.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *ReentrantLock lock = new ReentrantLock();当lock声明位置为成员变量时
        lock.tryLock(Long time,TimeUnit unit)
        与tryLock() 不同的是，tryLock(Long time,TimeUnit unit) 可以响应中断，即支持对获取锁的中断，
        但尝试获取一个内部锁的操作（进入一个synchronized块）是不能被中断的
        尝试获取锁，获取到锁，返回 true，否则返回false

 output:
 time=1512267029282当前线程A获取了锁。。。
 time=1512267031282当前线程：B被中断。。。。
 time=1512267034282当前线程A释放了锁。。。
 */
public class ReentrantLockTest4 {

    private ReentrantLock lock = new ReentrantLock();//lock声明位置为成员变量

    public static void main(String[] args) {

        ReentrantLockTest4 reentrantLockTest = new ReentrantLockTest4();
        MyThread a = new MyThread(reentrantLockTest, "A");
        MyThread b = new MyThread(reentrantLockTest, "B");
        a.start();
        b.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.interrupt();//主线程在等待2秒后中断线程B 获取锁
    }

    public void insert(Thread thread) throws InterruptedException {
        if (lock.tryLock(4 , TimeUnit.SECONDS)) {
            try {
                System.out.println("time="+System.currentTimeMillis()+"当前线程"+thread.getName()+"获取了锁。。。");

                long now = System.currentTimeMillis();

                while(System.currentTimeMillis()-now<5000){
                    //空跑5秒
                }
            }finally {
                lock.unlock();
                System.out.println("time="+System.currentTimeMillis()+"当前线程"+thread.getName()+"释放了锁。。。");
            }
        } else {
            System.out.println("time="+System.currentTimeMillis()+"当前线程"+thread.getName()+"获取锁失败。。。");
        }
    }
}

class MyThread extends Thread{

    private ReentrantLockTest4 test;

    public MyThread(ReentrantLockTest4 test,String name){
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
