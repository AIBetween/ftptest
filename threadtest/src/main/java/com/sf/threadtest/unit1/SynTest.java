package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/4/3.
 */
public class SynTest {

    public static void main(String[] args) {

        Obj1 obj1 = new Obj1();

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                try {
                    obj1.methodA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                try {
                    obj1.methodB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        thread1.start();
        thread2.start();
    }
}


class Obj1 {

    /**
     * 对象锁，
     * @throws InterruptedException
     */

    public synchronized void methodA() throws InterruptedException {

        System.out.println(String.format("%s start methodA", Thread.currentThread().getName()));

        Thread.sleep(1000);

        System.out.println(String.format("%s end methodA", Thread.currentThread().getName()));

    }

    public synchronized void methodB() throws InterruptedException {

        System.out.println(String.format("%s start methodB", Thread.currentThread().getName()));

        Thread.sleep(1000);

        System.out.println(String.format("%s end methodB", Thread.currentThread().getName()));

    }
}
