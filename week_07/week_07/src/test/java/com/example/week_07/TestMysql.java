package com.example.week_07;


import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

@SpringBootTest
public class TestMysql {


    @Test
    public void test1() {

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mydata");//mysql
        hikariConfig.setJdbcUrl("jdbc:mysql://bj-cynosdbmysql-grp-cxo8ezq6.sql.tencentcdb.com:26937/test");//oracle
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setUsername("python_cron");
        hikariConfig.setPassword("a305512775.");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{

            Random random = new Random(1);
            for (int i = 0; i < 10; i++) {
                //创建connection
                conn = ds.getConnection();
                statement = conn.createStatement();

                int i1 = random.nextInt(9999);

                long l = System.currentTimeMillis() / 1000L;

                //执行sql
                statement.execute("insert into test.orders (user_id, commodities, status, deliver_status, total_price, create_time, update_time)\n" +
                        "            VALUES ("+i1+", '{\"key\": \"value\"}', 0, '{\"key\":\"value\"}', 1, "+l+" , "+l+"); ");
            }


            //关闭connection
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
