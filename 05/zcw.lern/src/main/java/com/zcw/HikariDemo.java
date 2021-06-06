package com.zcw;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HikariDemo {

    public static void main(String[] args) {

        //配置文件
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mydata");//mysql
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/smzdm");//oracle
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

            //创建connection
            conn = ds.getConnection();
            statement = conn.createStatement();

            //执行sql
            rs = statement.executeQuery("select id,e_name  from mdr_enterprise_info");

            //取数据
            if (rs.next()){
                System.out.println(rs.getString("id"));
            }

            //关闭connection
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
