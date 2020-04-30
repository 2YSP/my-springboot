package cn.sp.test;

import cn.sp.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by 2YSP on 2020/3/12.
 * function接口练习
 */
public class FunctionTest {


    public static void main(String[] args) {
        testApply();
    }

    public static void testApply() {
        Function<User, String> userStringFunction = user -> user.getName();
        List<User> userList = Arrays.asList(
                new User("张三", 20),
                new User("李四", 26),
                new User("王五", 24),
                new User("赵六", 21)
        );
        List<String> nameList = convertUserListToNameList(userList, userStringFunction);
        nameList.forEach(System.out::println);
    }

    public static List<String> convertUserListToNameList(List<User> users, Function<User, String> mapper) {
        List<String> nameList = new ArrayList<>();
        users.forEach(user -> nameList.add(mapper.apply(user)));
        return nameList;
    }


}
