package cn.sp.vo;

import cn.sp.exception.BusinessException;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by 2YSP on 2019/8/28.
 */
@Data
public class CommonResult<T> implements Serializable{

  private int code;

  private String message;

  private T data;

  public CommonResult(){

  }

  public CommonResult(T data){
    this.data = data;
    this.code = 200;
    this.message = "success";
  }


  public static <T> CommonResult<T> errorResult(BusinessException exception){
    CommonResult<T> commonResult = new CommonResult<>();
    commonResult.code = exception.getErrorCode();
    commonResult.message = exception.getErrorMsg();
    return commonResult;
  }


}
