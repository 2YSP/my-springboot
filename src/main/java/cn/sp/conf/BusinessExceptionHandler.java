package cn.sp.conf;

import cn.sp.exception.BusinessException;
import cn.sp.vo.CommonResult;
import java.util.List;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by 2YSP on 2019/9/3.
 */
@RestControllerAdvice
public class BusinessExceptionHandler {

  @ExceptionHandler(Exception.class)
  public CommonResult<Void> handlerBusinessException(Exception exception) {
    return CommonResult.errorResult(transferToBusinessException(exception));
  }

  private BusinessException transferToBusinessException(Exception exception) {
    BusinessException businessException;
    if (exception instanceof BusinessException) {
      businessException = (BusinessException) exception;

    } else if (exception instanceof BindException) {
      BindException bindException = (BindException) exception;
      BindingResult bindingResult = bindException.getBindingResult();
      businessException = new BusinessException(getErrorMsg(bindingResult));

    } else if (exception instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException validException = (MethodArgumentNotValidException) exception;
      BindingResult bindingResult = validException.getBindingResult();
      businessException = new BusinessException(getErrorMsg(bindingResult));

    } else {
      businessException = new BusinessException(exception.getMessage());
    }
    return businessException;
  }

  private String getErrorMsg(BindingResult bindingResult) {
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    StringBuilder sb = new StringBuilder();
    fieldErrors.forEach(fieldError -> {
      sb.append(fieldError.getDefaultMessage());
      sb.append("-");
    });
    return sb.toString();
  }


}
