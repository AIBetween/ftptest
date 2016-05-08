package com.sf.threadtest.unit3;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示 线程被notigy后 如果不重新判断 是会出现问题的
 * Created by CodeMonkey on 2016/4/4.
 */
public class WaitNotifyTest2 {

    public static void main(String[] args) throws InterruptedException {

        Entry entry = new Entry();

        Thread thread1 = new Thread(() -> {

            try {
                entry.sub();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {

            try {
                entry.sub();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {


            for (int i = 0; i < 10; i++) {

                entry.add("zhangsan");
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(90);

        thread3.start();

    }
}


class Entry {

    private List<String> usernames = new ArrayList<>();

    public void add(String username) {

        synchronized (usernames) {

            usernames.add(username);

            /**
             * 这里只能使用唤醒一个，如果唤醒多个的话，下面的sub 就可能报错了。
             */
            usernames.notify();
        }
    }

    public void sub() throws InterruptedException {

        synchronized (usernames) {
            if (usernames.size() == 0) {

                usernames.wait();
            }

            usernames.remove(0);
        }

    }
}
