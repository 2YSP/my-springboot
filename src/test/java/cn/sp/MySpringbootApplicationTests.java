package cn.sp;

import cn.sp.entity.Company;
import cn.sp.entity.CompanyConverter;
import cn.sp.entity.CompanyDTO;
import cn.sp.entity.Employee;
import cn.sp.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringbootApplicationTests {

	@Autowired
	private MailService mailService;

	private String to = "ship@ubox.cn";

	@Test
	public void contextLoads() {
		mailService.sendSimpleMail(to,"内容：简单邮件测试","主题：邮件测试");
	}

	@Test
	public void mapStructTest(){
		Employee employee = new Employee("zhangsan",20);
		Company company = new Company();
		company.setId(1);
		company.setEmployee(employee);
		company.setName("yiyun");
		company.setCreateTime(new Date());

		CompanyDTO companyDTO = CompanyConverter.INSTANCE.domain2DTO(company);
		System.out.printf(companyDTO.toString());
	}
}
