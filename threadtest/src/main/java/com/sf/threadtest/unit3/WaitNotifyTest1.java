package com.sf.threadtest.unit3;

/**
 * one thread to wait and one thread to notify the thread that is waiting for
 * Created by CodeMonkey on 2016/4/4.
 */
public class WaitNotifyTest1 {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        MyThread1 myThread1 = new MyThread1(lock);
        MyThread2 myThread2 = new MyThread2(lock);

        myThread1.start();

        Thread.sleep(800);

        myThread2.start();
    }
}

class MyThread1 extends Thread{

    private Object lock;

    public MyThread1(Object lock) {

        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock) {

            System.out.println("thread 1 start waiting!");

            try {

                /**
                 * 要使用被锁的对象都wait 或者 notify
                 */
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread1 stop waiting!");
        }
    }
}

class MyThread2 extends Thread {

    private Object lock;

    public MyThread2(Object lock) {

        this.lock = lock;
    }

    @Override
    public void run() {
        super.run();
        synchronized (lock) {

            System.out.println("thread2 start notifying!");

            lock.notify();

            System.out.println("thread2 end notitying!");
        }

    }
}
