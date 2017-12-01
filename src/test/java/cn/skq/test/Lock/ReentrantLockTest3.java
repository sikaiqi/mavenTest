package cn.skq.test.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *ReentrantLock lock = new ReentrantLock();当lock声明位置为成员变量时
        lock.tryLock()
        尝试获取锁，获取到锁，返回 true，否则返回false

 output:
 当前线程A获取了锁。。。
 当前线程B获取锁失败。。。
 当前线程A释放了锁。。
 */
public class ReentrantLockTest3 {

    private List<Integer>  list  = new ArrayList<Integer>();
    private ReentrantLock lock = new ReentrantLock();//lock声明位置为成员变量

    public static void main(String[] args) {

        final ReentrantLockTest3 reentrantLockTest = new ReentrantLockTest3();

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
        if (lock.tryLock()) {
            try {
                System.out.println("当前线程"+thread.getName()+"获取了锁。。。");
                for (int i=0;i<5;i++){
                    list.add(i);
                }
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("当前线程"+thread.getName()+"释放了锁。。。");
            }
        } else {
            System.out.println("当前线程"+thread.getName()+"获取锁失败。。。");
        }
    }
}
