package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/4/4.
 */
public class InnerClassSyn {


    public class InnerClass {

        public synchronized void methodA() {

            System.out.println("inner class methodA entry！");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("inner class methodA out!");
        }

        public synchronized void methodB() {

            System.out.println("inner class methodB entry！");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("inner class methodB out!");
        }
    }

    public static void main(String[] args) {

        InnerClass innerClass = new InnerClassSyn().new InnerClass();

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                innerClass.methodA();
            }
        });

        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                innerClass.methodB();
            }
        });

        thread1.start();
        thread2.start();
    }
}

