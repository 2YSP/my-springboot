package cn.sp.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by 2YSP on 2020/1/26.
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

  public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
    super(listenerContainer);
  }


  /**
   * 针对redis数据失效事件，进行数据处理
   */
  @Override
  public void onMessage(Message message, @Nullable byte[] pattern) {
    String expiredKey = message.toString();
    System.out.println("失效的key:" + expiredKey);
  }
}
