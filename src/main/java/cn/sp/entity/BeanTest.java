package cn.sp.entity;

import lombok.Data;

/**
 * Created by 2YSP on 2019/4/28.
 */
@Data
public class BeanTest {

    private int id;

    private String name;

    @Override
    public String toString() {
        return "BeanTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
