package cn.sp.spi;

import com.mysql.jdbc.NonRegisteringDriver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 2YSP
 * @date 2020/6/30 20:24
 */
public class MyDriver extends NonRegisteringDriver {

    static {
        try {
            java.sql.DriverManager.registerDriver(new MyDriver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        System.out.println("准备创建数据库连接 url:"+url);
        System.out.println("JDBC配置信息:" + info);
        return super.connect(url, info);
    }

    /**
     * Construct a new driver and register it with DriverManager
     *
     * @throws SQLException if a database error occurs.
     */
    public MyDriver() throws SQLException {
    }


}
