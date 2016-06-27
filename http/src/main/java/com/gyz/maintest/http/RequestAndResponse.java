package com.gyz.maintest.http;

import org.apache.http.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/5/27 11:09.
 */
public class RequestAndResponse {

    public void testBasicHttpRequest() {

        BasicHttpRequest basicHttpRequest = new BasicHttpRequest("get", "www.baidu.com", HttpVersion.HTTP_1_1);

        System.out.println(basicHttpRequest.getProtocolVersion());
        System.out.println(basicHttpRequest.getRequestLine().getMethod());
        System.out.println(basicHttpRequest.getRequestLine().getUri());
        System.out.println(basicHttpRequest.getRequestLine());

    }

    public void testResponse() {

        BasicHttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
        System.out.println(response.getStatusLine());
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());

        System.out.println("--------------------------");

        //测试相应头
        response.addHeader("Age", "wrwrwr");
        response.addHeader("Age", "wrwrwr");
        response.addHeader("Age", "wrwrwr");
        response.addHeader("Age", "wrwrwr");

        for (Header header : response.getAllHeaders()) {

            System.out.println(header.getName());
            System.out.println(header.getValue());
        }
    }

    public void testEntity() throws IOException {

        StringEntity stringEntity = new StringEntity("nihao zhangsan", StandardCharsets.UTF_8);
        System.out.println(EntityUtils.toString(stringEntity, StandardCharsets.UTF_8));
        System.out.println(stringEntity.getContentLength());
        System.out.println(stringEntity.getContentEncoding());
        System.out.println(stringEntity.getContentType());
        for (HeaderElement headerElement : stringEntity.getContentType().getElements()) {

            System.out.println(headerElement.getName() + ":" + headerElement.getValue());
        }


        //资源释放
        try (InputStream inputStream = stringEntity.getContent();) {

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testConnect() {

        try {
            Socket socket = new Socket("www.baidu.com", 80);

            DefaultBHttpClientConnection defaultBHttpClientConnection = new DefaultBHttpClientConnection(8 * 1024);

            defaultBHttpClientConnection.bind(socket);

            System.out.println(defaultBHttpClientConnection.isOpen());
            HttpConnectionMetrics metrics = defaultBHttpClientConnection.getMetrics();
            System.out.println(metrics.getRequestCount());
            System.out.println(metrics.getResponseCount());
            System.out.println(metrics.getReceivedBytesCount());
            System.out.println(metrics.getSentBytesCount());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void testConn2() {

        try {
            Socket socket = new Socket("183.58.18.36", 80);
            DefaultBHttpClientConnection connection = new DefaultBHttpClientConnection(8 * 1024);
            connection.bind(socket);

            BasicHttpRequest request = new BasicHttpRequest("GET",
                    "http://tongji-res1.meizu.com/resources/tongji/flow.js", HttpVersion.HTTP_1_1);

            System.out.println(request.getRequestLine());

            request.addHeader("Referer", "http://www.meizu.com/");
            connection.sendRequestHeader(request);

            HttpResponse httpResponse = connection.receiveResponseHeader();
            System.out.println(httpResponse.getStatusLine().getStatusCode());
            System.out.println(httpResponse.getStatusLine().getReasonPhrase());

            connection.receiveResponseEntity(httpResponse);

            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {

                try (InputStream content = entity.getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(content));){

                    String line = "";
                    while (null != (line = reader.readLine())) {

                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {


        new RequestAndResponse().testConn2();

    }
}
