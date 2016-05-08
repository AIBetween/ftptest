package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/4/4.
 */
public class StaticSynTest {

    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                try {
                    printOjb.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                try {
                    printOjb.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        printOjb printOjb = new printOjb();
        Thread thread3 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                try {
                    printOjb.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class printOjb {

    public synchronized static void printA() throws InterruptedException {

        System.out.println("entry printA!");

        Thread.sleep(1000);

        System.out.println("out printA");
    }


    public synchronized static void printB() throws InterruptedException {

        System.out.println("entry printB!");

        Thread.sleep(1000);

        System.out.println("out printB");
    }

    /**
     * 发现执行该方法，是和static方法是不同步的。原因是一个是取得class锁。一个是取得对象锁。
     * 两个监视器是不一样的。故，是不同步的。
     * @throws InterruptedException
     */
    public synchronized void printC() throws InterruptedException {

        System.out.println("entry printC!");

        Thread.sleep(1000);

        System.out.println("out printC");
    }
}
