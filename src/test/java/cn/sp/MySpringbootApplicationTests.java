package cn.sp;

import cn.sp.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
