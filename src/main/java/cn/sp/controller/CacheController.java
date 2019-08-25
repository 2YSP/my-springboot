package cn.sp.controller;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 2YSP on 2019/8/25.
 */
@Controller
public class CacheController {

  @RequestMapping("/cache")
  public ResponseEntity<String> cache(
      @RequestHeader(value = "IF-Modify-Since", required = false) Date ifModifySince)
      throws Exception {

    DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    // 文档最后修改时间（去掉毫秒值）
    long lastModifiedMillis = getLastModified() / 1000 * 1000;
    // 当前系统时间（去掉毫秒值）
    long now = System.currentTimeMillis() / 1000 * 1000;
    // 文档可以在游览器端/proxy上缓存多久（单位：秒）
    long maxAge = 20;

    // 判断内容是否修改了，此处使用等值判断
    if (ifModifySince != null && ifModifySince.getTime() == lastModifiedMillis) {
      MultiValueMap<String, String> headers = new HttpHeaders();
      // 当前时间
      headers.add("Date", dateFormat.format(new Date(now)));
      // 过期时间，http1.0支持
      headers.add("Expires", dateFormat.format(new Date(now + maxAge * 1000)));
      // 文档生存时间,http1.1支持
      headers.add("Cache-Control", "max-age=" + maxAge);
      return new ResponseEntity<>(headers, HttpStatus.NOT_MODIFIED);
    }

    String body = "<a href=''>点击访问链接</a>";
    MultiValueMap<String, String> headers = new HttpHeaders();
    // 当前时间
    headers.add("Date", dateFormat.format(new Date(now)));
    // 文档修改时间
    headers.add("Last-Modified", dateFormat.format(new Date(lastModifiedMillis)));
    // 过期时间，http1.0支持
    headers.add("Expires", dateFormat.format(new Date(now + maxAge * 1000)));
    // 文档生存时间,http1.1支持
    headers.add("Cache-Control", "max-age=" + maxAge);
    return new ResponseEntity<>(body,headers,HttpStatus.OK);
  }

  Cache<String, Long> lastModifiedCache = CacheBuilder.newBuilder()
      .expireAfterWrite(10, TimeUnit.SECONDS)
      .build();

  private long getLastModified() throws ExecutionException {
    return lastModifiedCache.get("lastModifiedSince", () -> {
      return System.currentTimeMillis();
    });
  }
}
