package com;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Random;

public class TestMySql {


    public static void main(String[] args) {
        test2();
    }



    public static void test1() {

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mydata");//mysql
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3308/test");//oracle
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            long start = System.currentTimeMillis();
            Random random = new Random(1);

            conn = ds.getConnection();
            conn.setAutoCommit(false);

            statement = conn.createStatement();
            for (int i = 0; i < 1000000; i++) {
                //创建connection

                int i1 = random.nextInt(9999);

                long l = System.currentTimeMillis() / 1000L;

                //执行sql
                statement.addBatch("insert into test.orders (user_id, commodities, status, deliver_status, total_price, create_time, update_time)\n" +
                        "            VALUES ("+i1+", '{\"key\": \"value\"}', 0, '{\"key\":\"value\"}', 1, "+l+" , "+l+"); ");

            }
            statement.executeBatch();
            conn.commit();
            System.out.println("单次执行100W数据耗时" + (System.currentTimeMillis() - start));

            //关闭connection
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void test2() {

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mydata");//mysql
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3308/test");//oracle
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            long start = System.currentTimeMillis();
            Random random = new Random(1);

            System.out.println(LocalDateTime.now().toString() + "获得数据库链接");
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            statement = conn.createStatement();
            for (int i = 0; i < 1000000; i++) {
                //创建connection

                int i1 = random.nextInt(9999);

                long l = System.currentTimeMillis() / 1000L;

                //执行sql
                statement.addBatch("insert into test.orders (user_id, commodities, status, deliver_status, total_price, create_time, update_time)\n" +
                        "            VALUES ("+i1+", '{\"key\": \"value\"}', 0, '{\"key\":\"value\"}', 1, "+l+" , "+l+"); ");

                if (i % 5000 == 0) {
                    statement.executeBatch();
                }

            }
            System.out.println(LocalDateTime.now().toString() + "100W已提交");
            statement.executeBatch();
            System.out.println(LocalDateTime.now().toString() + "执行batch");
            conn.commit();
            System.out.println(LocalDateTime.now().toString() + "提交事务");
            System.out.println(LocalDateTime.now().toString() + "单次执行100W数据耗时" + (System.currentTimeMillis() - start));

            //关闭connection
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
