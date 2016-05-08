package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/3/29.
 */
public class MutilThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new MyThread1());
        thread.setName("runnable");
        thread.start();
        for (int i = 0; i < 100; i++) {

            int time = (int) (Math.random() * 1000);
            try {
                thread.sleep(time);
                System.out.println(thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class MyThread implements Runnable{


    public void run() {
        for (int i = 0; i < 100; i++) {

            int time = (int) (Math.random() * 1000);
            try {
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
