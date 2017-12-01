package cn.skq.test.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *ReentrantLock lock = new ReentrantLock();当lock声明位置为局部变量时
        lock.lock();

 output:
 当前线程A获取了锁。。。
 当前线程B获取了锁。。。
 当前线程A释放了锁。。。
 当前线程B释放了锁。。。
 */
public class ReentrantLockTest1 {

    private List<Integer>  list  = new ArrayList<Integer>();

    public static void main(String[] args) {

        final ReentrantLockTest1 reentrantLockTest = new ReentrantLockTest1();

        new Thread("A"){
            public void run(){
                reentrantLockTest.insert(Thread.currentThread());
            }
        }.start();

        new Thread("B"){
            public void run(){
                reentrantLockTest.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread){
        ReentrantLock lock = new ReentrantLock();
        /*
            注意此lock的位置：为方法内的局部变量
            所以该局部变量在每个线程都会创建一个副本，通过自己的副本获取的lock锁自然也不会相同
            不能将不同线程同步
         */

        lock.lock();

        try {
            System.out.println("当前线程"+thread.getName()+"获取了锁。。。");
            for (int i=0;i<5;i++){
                list.add(i);
            }
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("当前线程"+thread.getName()+"释放了锁。。。");
        }
    }
}
