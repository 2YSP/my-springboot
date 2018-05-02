package cn.sp.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2018/5/2
 */
@Component
@Slf4j
public class KafkaReceiver {

  @KafkaListener(topics = {"ship"})
  public void listen(ConsumerRecord<?,?> record){
      Optional<?> kafkaMessage = Optional.ofNullable(record.value());
      if (kafkaMessage.isPresent()){
          Object message = kafkaMessage.get();
          log.info("===========record:{}",record);
          log.info("===========message:{}",message);
      }
  }
}
