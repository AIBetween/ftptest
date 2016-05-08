package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/4/3.
 */
public class CodeBlockSynTest {

    public static void main(String[] args) {

        Obj2 obj2 = new Obj2("zhangsan");

        Thread thread1 = new Thread(() -> {


            for (int i = 0; i < 10; i++) {

                try {
                    obj2.methodA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {


            for (int i = 0; i < 10; i++) {

                try {
                    obj2.methodB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();

        thread2.start();
    }
}

class Obj2 {


    public Obj2(String username) {
        this.username = username;
    }

    private String username;

    public void methodA() throws InterruptedException {

        System.out.println("A out block!");

        synchronized (username) {

            System.out.println("synA block start!");

            Thread.sleep(1000);

            System.out.println("synA block end!");
        }
    }

    public void methodB() throws InterruptedException {

        System.out.println("B out block!");

        synchronized (username) {

            System.out.println("synB block start!");

            Thread.sleep(1000);

            System.out.println("synB block end!");
        }
    }
}
