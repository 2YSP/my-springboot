package cn.sp.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by 2YSP on 2019/8/11.
 */
@JobHandler(value = "demoJobHandler")
@Component
@Slf4j
public class XXLJobDemo extends IJobHandler {

  @Override
  public ReturnT<String> execute(String param) throws Exception {
    log.info("==================execute job success");
    return ReturnT.SUCCESS;

  }
}
