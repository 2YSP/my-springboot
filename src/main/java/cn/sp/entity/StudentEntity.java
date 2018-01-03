package cn.sp.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 2YSP
 * @Description: 要导出的对象
 * @Date: Created in 2018/1/3
 */
public class StudentEntity implements Serializable{

    private String id;

    @Excel(name = "学生姓名",height = 20,width = 30,isImportField = "true_st")
    private String name;

    @Excel(name = "学生性别",replace = {"男_1","女_2"},suffix = "生",isImportField = "true_st")
    private int sex;

    @Excel(name = "出生日期",format = "yyyy-MM-dd",width = 20,isImportField = "true_st")
    private Date birthday;

    @Excel(name = "进校日期",format = "yyyy-MM-dd")
    private Date registrationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
