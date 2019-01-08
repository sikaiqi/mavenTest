package cn.skq.test.Atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by skq on 2018/8/8.
 */
public class AtomicTest {

    public static void main(String[] args) throws InterruptedException {
        atomicABAproblemTest();
    }

    /**
     * AtomicInteger 底层使用了CAS 来保证并发时的数据一致性
     * 但CAS 存在短板 ，会有ABA 问题
     * 测试ABA 问题
     */
    private static void atomicABAproblemTest() throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger(100);
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100 ,1);

        // A -> B -> A
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(100, 110);
                atomicInteger.compareAndSet(110, 100);
            }
        });

        // A -> B，t1 已经将100—>110->100 ,2个100 语义上是不一样的，这时候t2 是否可以更新成功？
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("AtomicInteger:"+ atomicInteger.compareAndSet(100, 110));
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedReference.compareAndSet(100,110,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);

            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });

    }

}
