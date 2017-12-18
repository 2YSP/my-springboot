package cn.sp;

import cn.sp.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2017/12/18
 */
public class JacksonTest {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        person.setId(1);
        person.setName("lisi");
        person.setAge(18);
        person.setSex("女");
        person.setBirthday(new Date());

        //对象序列化为 json string
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);//缩进输出
        try {
            String jsonStr = objectMapper.writeValueAsString(person);
            System.out.println(jsonStr);

            //json string to obj
            Person person1 = objectMapper.readValue(jsonStr, Person.class);
            System.out.println(person1.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
