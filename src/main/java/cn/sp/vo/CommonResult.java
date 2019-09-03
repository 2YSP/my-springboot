package cn.sp.vo;

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


  public static <T> CommonResult<T> errorResult(int errorCode,String errorMsg){
    CommonResult<T> commonResult = new CommonResult<>();
    commonResult.code = errorCode;
    commonResult.message = errorMsg;
    return commonResult;
  }
}
