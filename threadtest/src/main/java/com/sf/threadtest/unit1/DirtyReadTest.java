package com.sf.threadtest.unit1;

import java.text.MessageFormat;

/**
 * Created by CodeMonkey on 2016/4/3.
 */
public class DirtyReadTest {



    public static void main(String[] args) {

        User2 user = new User2();
        Thread thread1 = new Thread(() -> {

            user.setUsername("aa");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            user.setPassword("AA");

            System.out.println(String.format("%s end user:%S"
                    , Thread.currentThread().getName()
                    , user.toString()));
        }, "thread1");

        Thread thread2 = new Thread(() -> {

            user.setPassword("BB");
            /**
             * 中间打印出现脏读。
             */
            System.out.println(user.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            user.setUsername("bb");

            System.out.println(String.format("%s end user:%S"
                    , Thread.currentThread().getName()
                    , user.toString()));

        });

        thread1.start();
        thread2.start();
    }
}



class User2 {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return MessageFormat.format("[username:{0}, password:{1}]", username, password);
    }
}
