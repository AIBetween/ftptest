package com.sf.threadtest.unit1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CodeMonkey on 2016/4/4.
 */
public class AtomicTest {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();

        Thread thread1 = new Thread(() -> {

            for(int i = 0; i < 10; i++) {

                atomicInteger.getAndIncrement();
                System.out.println("increment 1:" + atomicInteger.get());
            }
        });

        Thread thread2 = new Thread(() -> {

            for(int i = 0; i < 10; i++) {

                atomicInteger.getAndIncrement();
                System.out.println("increment 1:" + atomicInteger.get());
            }
        });


        thread1.start();
        thread2.start();
    }
}

