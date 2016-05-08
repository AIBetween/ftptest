package com.sf.threadtest.unit3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CodeMonkey on 2016/4/10.
 */
public class ConditionTest {

    public static void main(String[] args) {

        PrintServer printServer = new PrintServer();
        for (int i = 0; i < 10; i++) {

            Thread thread1 = new Thread(printServer::printZhangsan, "zhangsan thread" + i);

            Thread thread2 = new Thread(printServer::printLisi, "lisi thread" + i);

            Thread thread3 = new Thread(printServer::printWangwu, "wangwu thread" + i);

            thread1.start();
            thread2.start();
            thread3.start();
        }
    }
}

class PrintServer {

    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private int nextWho = 0;
    public void printZhangsan() {

        lock.lock();
        try {
            while (nextWho != 0) {

                conditionA.await();
            }

            System.out.println("i am zhangsan " + Thread.currentThread().getName());
            nextWho = 1;
            conditionB.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }

    public void printLisi() {

        lock.lock();
        try {
            while (nextWho != 1) {

                conditionB.await();
            }

            System.out.println("i am LISI " + Thread.currentThread().getName());
            nextWho = 2;
            conditionC.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }


    public void printWangwu() {

        lock.lock();
        try {
            while (nextWho != 2) {

                conditionC.await();
            }

            System.out.println("i am wangwu " + Thread.currentThread().getName());
            nextWho = 0;
            conditionA.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }
}
