package com.sf.threadtest.unit3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CodeMonkey on 2016/4/10.
 */
public class ReentrantLockTest {

    public static void main(String[] args) {

        BaseServer baseServer = new BaseServer();

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(() -> {

                try {
                    baseServer.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "savethread" + i);

            thread.start();
        }

        for (int i = 0; i < 10; i++) {



            Thread thread = new Thread(() -> {

                try {
                    baseServer.save();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "getthread" + i);

            thread.start();

        }

    }
}

class BaseServer extends Thread {

    private ReentrantLock lock = new ReentrantLock(false);
    private boolean isSave = false;
    private Condition condition = lock.newCondition();

    public void save() throws InterruptedException {

        lock.lock();
        while (isSave) {

            condition.await();
        }

        System.out.println("save data! " + Thread.currentThread().getName());
        isSave = true;
        condition.signalAll();

        lock.unlock();
    }

    public void get() throws InterruptedException {

        lock.lock();

        while (!isSave) {

            condition.await();
        }

        System.out.println("get data! " + Thread.currentThread().getName());
        isSave = false;

        condition.signalAll();

        lock.unlock();
    }
}
