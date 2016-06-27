package com.gyz.maintest.http.exemutlithread;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/6/7 14:35.
 */
public class InvalidRemove extends Thread{

    private PoolingHttpClientConnectionManager connectionManager;
    private boolean isShutdown;

    public InvalidRemove() {

        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(200);
    }

    @Override
    public void run() {

        while (!isShutdown) {

            synchronized (this) {

                try {
                    this.wait(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //关闭失效的连接
                connectionManager.closeExpiredConnections();
                //关闭闲置5秒钟的连接
                connectionManager.closeIdleConnections(5, TimeUnit.SECONDS);

                System.out.println("close expired connection successful!!");
            }
        }
    }

    //关闭 connectionManager 监控
    public void shutDown() {

        if (!isShutdown) {

            synchronized (this) {

                isShutdown = true;

                this.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        InvalidRemove invalidRemoveThread = new InvalidRemove();
        invalidRemoveThread.start();

        Thread.sleep(5000);

        invalidRemoveThread.shutDown();
    }
}
