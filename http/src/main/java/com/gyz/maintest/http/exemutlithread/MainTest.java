package com.gyz.maintest.http.exemutlithread;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/6/7 13:56.
 */
public class MainTest {

    public static CloseableHttpClient httpClient;

    static {

        //提供连接池来满足多线程的需求。
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(10);

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }

    public static void main(String[] args) {

        String[] urls = {
                "http://www.meizu.com",
                "http://www.baidu.com",
                "http://www.hao123.com",
                "http://www.fang.com",
                "http://www.qq.com",
                "http://www.taobao.com",
                "http://www.al.com"
        };

        for (String url : urls) {

            new Thread(() -> {

                HttpClientContext clientContext = HttpClientContext.create();
                HttpGet httpGet = new HttpGet(url);
                try {
                    CloseableHttpResponse response = httpClient.execute(httpGet, clientContext);

                    System.out.println("######################" + EntityUtils.toString(response.getEntity()).substring(0, 100)
                            + "###########################\n\n\n\n\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
