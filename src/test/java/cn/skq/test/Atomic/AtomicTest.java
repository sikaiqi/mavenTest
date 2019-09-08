package cn.skq.test.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/8/6 0006.
 */
public class AtomicTest{

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                new AtomicInteger(100).compareAndSet(100,101);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {

            }
        });
    }


}
