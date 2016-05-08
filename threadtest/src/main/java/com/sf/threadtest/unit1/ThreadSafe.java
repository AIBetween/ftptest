package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/3/29.
 */
public class ThreadSafe {

    public static void main(String[] args) throws InterruptedException {


        User user = new User("zhangsan", 16, "china");

        Thread thread = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                user.setAge(user.getAge() + 1);
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                user.setAge(user.getAge() + 1);
            }
        }, "thread2");

        Thread thread3 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {

                user.setAge(user.getAge() + 1);
            }
        }, "thread3");


        System.out.println("thread3 is alive:" + thread3.isAlive());

        thread.start();
        thread2.start();
        thread3.start();

        Thread.sleep(10000);

        System.out.println("thread3 is alive:" + thread3.isAlive());

    }


}

class User{

    private String username;
    private int age;
    private String address;

    public User(String username, int age, String address) {
        this.username = username;
        this.age = age;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public synchronized void setAge(int age) {

        if (this.age >= 30) {

            return;
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.age++;
        System.out.println(String.format("%s add 1,age:%d", Thread.currentThread().getName(), this.age));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
