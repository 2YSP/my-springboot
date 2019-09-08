package cn.sp.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;

/**
 * Created by 2YSP on 2019/9/8.
 */
public class HttpClientTest {

  static PoolingHttpClientConnectionManager manager = null;
  static CloseableHttpClient httpClient = null;

  public static synchronized CloseableHttpClient getHttpClient() {
    if (httpClient == null) {
      // 注册访问协议的工厂
      Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
          .register("http", PlainConnectionSocketFactory.INSTANCE)
          .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
          .build();
      // HttpConnection工厂： 配置写请求/解析响应处理器
      HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
          DefaultHttpRequestWriterFactory.INSTANCE, DefaultHttpResponseParserFactory.INSTANCE);
      // DNS解析器
      DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
      // 创建池化链接管理器
      manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory,
          dnsResolver);
      // 默认为Socket配置
      SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
      manager.setDefaultSocketConfig(defaultSocketConfig);

      // 设置整个连接池的最大连接数
      manager.setMaxTotal(300);
      // 每个路由的默认最大连接数，设置过小无法支持大并发
      manager.setDefaultMaxPerRoute(200);
      // 在从连接池获取链接时，连接不活跃多久时间后需要进行一次验证
      manager.setValidateAfterInactivity(5 * 1000);

      // 默认请求配置
      RequestConfig defaultRequestConfig = RequestConfig.custom()
          // 设置连接超时
          .setConnectTimeout(2 * 1000)
          // 设置等待数据超时时间
          .setSocketTimeout(5 * 1000)
          // 设置从连接池获取连接的等待超时时间
          .setConnectionRequestTimeout(2000)
          .build();

      httpClient = HttpClients.custom()
          .setConnectionManager(manager)
          // 连接池不是共享模式
          .setConnectionManagerShared(false)
          // 定期回收空闲连接
          .evictIdleConnections(60, TimeUnit.SECONDS)
          // 定期回收过期连接
          .evictExpiredConnections()
          // 连接存活时间，如果不设置则根据长连接信息决定
          .setConnectionTimeToLive(60, TimeUnit.SECONDS)
          .setDefaultRequestConfig(defaultRequestConfig)
          // 连接重用策略，即是否能keepAlive
          .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
          // 长连接配置
          .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
          // 设置重试次数，默认是3次，当前是禁用
          .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
          .build();

      // JVM停止或重启时，关闭连接池释放掉连接
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
          httpClient.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }));
    }
    return httpClient;
  }

}
