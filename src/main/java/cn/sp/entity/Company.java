package cn.sp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {

    private int id;

    private Date createTime;

    private String name;

    private Employee employee;
}
