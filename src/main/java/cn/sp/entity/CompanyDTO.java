package cn.sp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDTO {

    private int id;

    private String name;
    /**
     * company 是 createTime
     */
    private Date createdTime;

    private String createFormatTime;

    /**
     *
     */
    private String employeeName;

    private int employeeAge;

}
