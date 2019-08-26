package cn.sp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 2YSP on 2019/8/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayLoad {

  private Long userId;

  private String name;

  private Long exp;

}
