package com.sf.threadtest.unit1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeMonkey on 2016/4/3.
 */
public class ZhangsanTest {

    public static void main(String[] args) {

        Account account = new Account(500);

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                try {
                    account.draw(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 100; i++) {

                try {
                    account.draw(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();

        List<String> names = new ArrayList<>();
        names.add("zhangsan0");
        names.add("zhangsan1");
        names.add("zhangsan2");
        names.add("zhangsan3");
        names.add("zhangsan4");

        names.parallelStream()
                .filter(name -> {

                    if (Integer.parseInt(name.substring(name.length() - 1, name.length())) < 3) {

                        return true;
                    }else {

                        return false;
                    }
                })
                .map(name -> {

                    return "we love " + name;
                })
                .forEach(System.out::println);


    }
}


class Account {

    public Account() {
    }

    public Account(int number) {
        this.number = number;
    }

    private int number;

    public synchronized void draw(int drawNumber) throws InterruptedException {

        if (number >= drawNumber) {

            Thread.sleep((int) (Math.random() * 1000));
            number = number - drawNumber;

            System.out.println(String.format("%s draw money %d, the number is %d",
                    Thread.currentThread().getName(), drawNumber, getNumber()));
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
