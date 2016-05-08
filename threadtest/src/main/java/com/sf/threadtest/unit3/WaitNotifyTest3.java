package com.sf.threadtest.unit3;

/**
 * Created by CodeMonkey on 2016/4/4.
 */
public class WaitNotifyTest3 {

    public static void main(String[] args) {

        Account account = new Account(0);

        Runnable saveRunnable = () -> {

            for (int i = 0; i < 10; i++) {

                try {
                    account.saveMoney();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++) {


            Thread saveThread = new Thread(saveRunnable);
            saveThread.start();
        }

        Runnable drawRunnable = () -> {

            for (int i = 0; i < 10; i++) {

                try {
                    account.drawMoney();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10; i++) {

            Thread drawThread = new Thread(drawRunnable);
            drawThread.start();
        }


    }
}

class Account {

    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public synchronized void saveMoney() throws InterruptedException {

        while (balance != 0) {

            this.wait();
        }

        balance = 100;
        System.out.println("save 100!");
        this.notifyAll();
    }

    public synchronized void drawMoney() throws InterruptedException {

        while (balance != 100) {

            this.wait();
        }

        balance -= 100;
        System.out.println("draw 100!");
        this.notifyAll();
    }
}
