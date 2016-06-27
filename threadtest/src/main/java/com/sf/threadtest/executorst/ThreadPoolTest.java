package com.sf.threadtest.executorst;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/5/25 11:42.
 */
public class ThreadPoolTest {

    private static ExecutorService threadPool;
    private static List<Future> threadCallResults = Collections.synchronizedList(new ArrayList<>());
    static {

        threadPool = Executors.newFixedThreadPool(10, runnable -> {

            Thread newThread = new Thread(Thread.currentThread().getThreadGroup(), runnable);
            newThread.setUncaughtExceptionHandler(new MyThreadExceptionHandle());
            return newThread;
        });
    }

    public static void submitTask(Runnable task) {

        Future<?> future = threadPool.submit(task);
        threadCallResults.add(future);
    }

    public static boolean isAllCalcEnd() {

        boolean result = true;

        for (Future future : threadCallResults) {

            if (!future.isDone()) {

                result = false;
                break;
            }
        }

        return result;
    }



    public static void main(String[] args) {


        for (int i = 0; i < 20; i++) {

            ThreadPoolTest.submitTask(()->{


                int randomNumber = (int) (Math.random() * 100);
                for (int j = 0; j < 100; j++) {

                    System.out.println(j);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (j == randomNumber) {

                        throw new RuntimeException("test");
                    }
                }


            });
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if (ThreadPoolTest.isAllCalcEnd()) {

                    System.out.println("############计算完毕##############");
                    System.exit(0);
                }else {

                    System.out.println("#############还没计算完毕#################");
                }
            }
        }, 0, 1000);
    }
}

class MyThreadExceptionHandle implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        System.out.println(String.format("%s appear exception!", t.getName()));
        e.printStackTrace();
    }
}
