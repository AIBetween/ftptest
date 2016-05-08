package com.sf.threadtest.unit3;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Created by CodeMonkey on 2016/4/10.
 */
public class GetQueueLengthTest {

    public static void main(String[] args) throws InterruptedException {

        MyServer3 myServer3 = new MyServer3();

        for (int i = 0; i < 10; i++) {

            new MyServer3().start();
        }

        Thread.sleep(1000);

        /**
         * 打印的结果是9。
         * getQueueLength 的方法的含义是：该线程是runnable的，等待取得锁的线程数量。
         */
        System.out.println("get queue length " + MyServer3.lock.getQueueLength());
        System.out.println(MyServer3.lock.getHoldCount());
    }
}

class MyServer3 extends Thread {

    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {

        if (lock.tryLock()) {

        }
        lock.lock();

        System.out.println(Thread.currentThread().getName() + " wait");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {

            lock.unlock();
        }
    }
}
