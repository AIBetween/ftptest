package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/3/30.
 */
public class CurrentThreadTest {

    public static void main(String[] args) throws InterruptedException {

        MyThread1 thread1 = new MyThread1("zhangsan");
        thread1.start();
        thread1.interrupt();
        Thread.sleep(10000);


    }
}


class MyThread1 extends Thread {

    public MyThread1() {

    }

    public MyThread1(String name) {

        /**
         * 从这里可以看出 是由main 线程调用的。
         */
        super(name);
        System.out.println("currentThread:" + Thread.currentThread().getName());
        System.out.println("this name" + this.getName());
        System.out.println("this thread alive:" + this.isAlive());
        System.out.println("this.currentThread:" + Thread.currentThread().getName());

    }

    @Override
    public void run() {


        /**
         * this 表示本对象，this.currentThread 表示正在执行这段代码的线程。调用run（）和 start() 方法的输出结果是不一样的。<br>
         *     run() 那么这个方法会是main
         *     start() 就是本县城
         *
         */
        System.out.println("run:" + this.currentThread().getName());

        System.out.println("isinterrupted:" + Thread.interrupted());

        //test inputrupt method
        for (int i = 0; i < 100; i++) {

            if (i == 55) {

                if (Thread.interrupted()) {

                    System.out.println("被打断退出！");
                    break;
                }
            }

            System.out.println(i);
        }
    }
}
