package com.gyz.maintest.http;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/5/31 12:43.
 */
public class HttpClientTest {

    public void startHttpClient() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());

        /**
         * 这种是使用手动的方式关闭底层的流对象
         */
        if (response.getEntity() != null) {

            try (InputStream inputStream = response.getEntity().getContent();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));){
                 String line = "";

                while (null != (line = bufferedReader.readLine())) {

                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 使用reponseHandle 来处理相应。
     * @throws IOException
     */
    public static void testResponseHandle() throws IOException {

        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpClient httpClient = HttpClients.createDefault();


        /**
         * 使用responseHandler 保证 getContext 中的inpustream会在使用后关闭
         */
        try {
            String result = httpClient.execute(httpGet, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {

                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    if (statusCode >= 200 && statusCode <= 300) {

                        return EntityUtils.toString(httpResponse.getEntity());
                    } else {

                        return "error get";
                    }
                }
            });

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.close();
    }

    public static void testLogin() {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        CloseableHttpClient httpClient = HttpClients.createDefault();

        String url = String.format("http://10.202.33.66:1080/diamond/config.co?dataId=%s&group=%s",
                "fvp_fvptest", "dev");

        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

                response.getStatusLine().getStatusCode();
                return EntityUtils.toString(response.getEntity());
            }
        };

        try {
            String result = httpClient.execute(httpGet, responseHandler);

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testLogin2() {

        HttpPost httpPost = new HttpPost("http://10.202.33.66:1080/diamond/login.do?method=login");
        httpPost.addHeader("username","abc");
        httpPost.addHeader("password","fV8$NJO5");


        try {
            CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);

            System.out.println(httpResponse.getStatusLine());
            EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testForm() throws UnsupportedEncodingException {

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username","abc"));
        nameValuePairs.add(new BasicNameValuePair("password","fV8$NJO5"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
        urlEncodedFormEntity.setChunked(true);

        HttpPost httpPost = new HttpPost("http://10.202.33.66:1080/diamond/login.do?method=login");
        httpPost.setEntity(urlEncodedFormEntity);

        System.out.println(httpPost.getURI());
    }

    public void test() {

        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
        System.out.println("zhangsan23");
    }

    public static void createRequestGet() throws URISyntaxException, IOException {

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.baidu.com")
                .setPath("/")
                .setParameter("username", "zhangsan")
                .setParameter("password", "nihao")
                .build();

        HttpGet httpGet = new HttpGet(uri);

        System.out.println(httpGet.getURI());
        System.out.println(httpGet.getRequestLine());

        CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpGet);

        System.out.println(httpResponse.getStatusLine());
    }


    public static void testConnectionManage() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);//设置该连接池中最大的连接数量。
        connectionManager.setDefaultMaxPerRoute(20); // 设置每一个route最大的连接数
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("www.baidu.com")), 10);//为特殊的route设置连接数量。

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();



    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        testForm();
    }
}
