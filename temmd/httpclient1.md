#httpclient helloWorld
##httpclient maven 依赖
```xml
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.5.2</version>
</dependency>
```
##一个入门的例子
```java
CloseableHttpClient httpclient = HttpClients.createDefault();
HttpGet httpGet = new HttpGet("http://targethost/homepage");
CloseableHttpResponse response1 = httpclient.execute(httpGet);
try {
    System.out.println(response1.getStatusLine());
    HttpEntity entity1 = response1.getEntity();
    // do something useful with the response body
    // and ensure it is fully consumed
    EntityUtils.consume(entity1);
} finally {
    response1.close();
}
```
Note that 以上的代码可以使用fluent API替换。
```java
// The fluent API relieves the user from having to deal with manual deallocation of system
// resources at the cost of having to buffer response content in memory in some cases.

Request.Get("http://targethost/homepage")
    .execute().returnContent();
Request.Post("http://targethost/login")
    .bodyForm(Form.form().add("username",  "vip").add("password",  "secret").build())
    .execute().returnContent();

```
#正式的教程。
##httpclient

> 执行http request 和 接收请求的执行结果。

一个简单的例子。
```java

CloseableHttpClient httpclient = HttpClients.createDefault();
HttpGet httpget = new HttpGet("http://localhost/");
CloseableHttpResponse response = httpclient.execute(httpget);
try {
<...>
} finally {
response.close();
}

```
需要注意的是：
1. httpclient 实例是**线程安全**的，也就是说一个httpClient实例可以并发的发送请求。
2. 上面拿到的CloseableHttpClient 在你不需要使用的时候，是要手动关闭的（close方法）

##httpGet && httpResponse
you can use URIBuilder to simplify create URIS.

下面是一个简单的例子
```java

public static void createRequestGet() throws URISyntaxException {

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.baidu.com")
                .setPath("/")
                .setParameter("username", "zhangsan")
                .setParameter("password", "nihao")
                .build();

        HttpGet httpGet = new HttpGet(uri);
    }
```
需要注意的是，上面的httpGet 封装了基本的请求信息，例如请求行。如果打印请求行（httpGet.getRequestLine()），
打印的结果是（如果不明白自行百度）：
GET http://www.baidu.com/?username=zhangsan&password=nihao HTTP/1.1

上面的代码只是构建一个httpGet 请求，但是并没有自行该请求，下面我们执行这个get请求，并打印响应的状态行
```java
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
```
打印的结果是：
HTTP/1.1 200 OK（表明使用的http version 是1.1 状态码是 200，描述是OK）

##http form 处理
> 构建一个post请求，该post 请求中封装请求的参数。
```java
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        nameValuePairs.add(new BasicNameValuePair("username","abc"));

        nameValuePairs.add(new BasicNameValuePair("password","fV8$NJO5"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);

        HttpPost httpPost = new HttpPost("http://10.202.33.66:1080/diamond/login.do?method=login");

        httpPost.setEntity(urlEncodedFormEntity);

        System.out.println(httpPost.getURI());

```
##要求httpclient对传输实体进行编码验证

下面的代码会验证字符的编码是否正确。
```java

UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
urlEncodedFormEntity.setChunked(true);
```
##使用responseHandler

> 如果你使用了普通的方式对httpResponse 进行处理，并使用了resposne.getEntity.getContext()方法，那么使用完毕后必须操心去关闭流。
现在你可以使用responseHandler来处理这些琐事。这个处理器会确保使用完毕后，会关闭流，并返回连接。

```java
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
```
##关于httpClient可能抛出的异常
1. IOException。socket timeout和socket reset 都会导致抛出该异常，此异常是非致命、能够从异常中恢复过来。
2. httpException。如果违背 http 协议，则会抛出该异常。通常是致命，当然无法恢复的。httpclient 如果放生此类错误，会抛出其子类：ClientProtocolException。

##http连接管理PoolingHttpClientConnectionManager
1. 该连接pool 支持多线程的并发执行。
2. 连接池中可以保存多个 不同路由的连接信息，并将它们保存下来。
3. 通常情况下默认对于同一个route保存最多2个连接。总共不超过20个链接。

```java

public static void testConnectionManage() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);//设置该连接池中最大的连接数量。
        connectionManager.setDefaultMaxPerRoute(20); // 设置每一个route最大的连接数
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("www.baidu.com")), 10);//为特殊的route设置连接数量。

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }
```


###连接池中的连接是会失效的。
1. 使用poolConnectionManager 虽然连接会在每次使用完毕后归还，但是一旦该连接被服务器端关闭，那么该连接就会失效。
2. 客户端的失效检查，并不是100% 可靠的。但是你可以使用poolManager 中的closeExpiredConnections or closeIdleConnections关闭失效的连接。

下面的代码使用一个线程来完成对poolManager中失效的连接的清除。
```java

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
```

##满足多线程执行httpclient 示例代码。
1. 连接管理使用PoolingHttpClientConnectionManager
2. 为每一个线程 生成一个httpcontext 示例。

```java

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
```

