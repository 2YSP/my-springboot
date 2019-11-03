package cn.sp;

import cn.sp.entity.Company;
import cn.sp.entity.CompanyConverter;
import cn.sp.entity.CompanyDTO;
import cn.sp.entity.Employee;
import cn.sp.entity.Person;
import cn.sp.service.MailService;
import cn.sp.service.PersonService;
import com.github.pagehelper.Page;

import org.jasypt.encryption.StringEncryptor;
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

	@Autowired
	private StringEncryptor stringEncryptor;

  @Autowired
  private PersonService personService;

	@Test
	public void encrypt(){
		String encrypt = stringEncryptor.encrypt("1234");
		System.out.println(encrypt);
	}

	@Test
	public void decrypt(){
		String decrypt = stringEncryptor.decrypt("BLXumM2LzuSY+ZdLMZe+8w==");
		System.out.println(decrypt);
	}



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

	@Test
	public void pageHelperTest(){
		Page<Person> page = personService.queryPage(1, 3);
		System.out.println(page);
	}
}
