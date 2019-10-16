package cn.sp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by 2YSP on 2019/9/3.
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

  private int errorCode = 525;

  private String errorMsg;

  public BusinessException(String errorMsg){
    this.errorMsg = errorMsg;
  }


}
