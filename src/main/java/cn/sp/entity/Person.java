package cn.sp.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
@ApiModel
public class Person implements Serializable{
	@ApiModelProperty(value = "用户ID")
	private Integer id;

	@ApiModelProperty(value = "姓名",required = true)
	private String name;

	@ApiModelProperty(value = "性别")
	private String sex;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "生日")
	private Date birthday;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", birthday=" + birthday + "]";
	}
	
	
}
