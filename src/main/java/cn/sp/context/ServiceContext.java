package cn.sp.context;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.Data;

/**
 * Created by 2YSP on 2019/7/28.
 */
@Data
public class ServiceContext {

  private Long userId;

  private Map<String, Object> attributes = Maps.newHashMap();

}
