package cn.sp.conf;

import cn.sp.exception.BusinessException;
import cn.sp.vo.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by 2YSP on 2019/9/3.
 */
@RestControllerAdvice
public class BusinessExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public CommonResult<Void> handlerBusinessException(BusinessException businessException) {
    return CommonResult.errorResult(businessException.getErrorCode(),businessException.getErrorMsg());
  }

}
