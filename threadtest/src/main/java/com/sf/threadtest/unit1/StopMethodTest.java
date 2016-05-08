package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/3/31.
 */
public class StopMethodTest {

    public static void main(String[] args) throws InterruptedException {

        MyThread3 thread3 = new MyThread3("zhangsan");

        thread3.start();

        Thread.sleep(100);

        thread3.interrupt();
    }
}

class MyThread3 extends Thread {

    public MyThread3(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();

        try {

            for (int i = 0; i < 10000; i++) {

                if (Thread.interrupted()) {

                    System.out.println("thread interrupted!");

                    throw new InterruptedException("exception");
                }

                System.out.println("i " + i);
            }
        } catch (InterruptedException e) {

            System.out.println("catch");
        }
    }
}
