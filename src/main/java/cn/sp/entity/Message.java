package cn.sp.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2018/5/2
 */
@Data
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;
}
