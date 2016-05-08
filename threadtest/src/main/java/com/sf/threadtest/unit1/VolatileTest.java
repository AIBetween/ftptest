package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/4/4.
 */
public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {

        MyThread5 myThread5 = new MyThread5();

        Thread thread = new Thread(myThread5);
        thread.start();

        Thread.sleep(1000);

        myThread5.setAlive(false);
    }
}

class MyThread5 implements Runnable {

    private boolean isAlive = true;


    @Override
    public void run() {

        while (isAlive) {

            System.out.println("thread is alive!");
        }

        System.out.println("thread is end!");
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
