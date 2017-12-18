package cn.sp.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2017/12/18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)//不包含Null
public class BaseVo implements Serializable {
    @JsonIgnore
    private IResult result  = IResult.SUCCESS;

    private Integer resultCode;
    private String resultMsg;

    public Integer getResultCode() {
        return result == null ? null : result.getCode();
    }

    public String getResultMsg() {
        return result == null ? null : result.getMsg();
    }

    public IResult getResult() {
        return result;
    }

    public void setResult(IResult result) {
        this.result = result;
    }
}
