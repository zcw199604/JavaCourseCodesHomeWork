package com.zcw;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.di.jdbc.mapper.core.impl.JdbcMapperImpl;

public class JdbcFactoryBuilder {



    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smzdm", "root", "123456");
            DatabaseMetaData metaData = connection.getMetaData();
            // 设置不自动提交
            boolean autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("");
            ResultSet resultSet = statement.executeQuery("select id,e_name from mdr_enterprise_info ");
            List<Apple> apples = new ArrayList<Apple>();

            while (resultSet.next()) {
                Apple a = new JdbcFactoryBuilder().new Apple(resultSet.getString(1),resultSet.getString(2));
                apples.add(a);
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(new Date());

            Statement statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            long time3 = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                statement1.execute(" update mdr_enterprise_info set checked_time = '"+ format+"'"
                        +" where id = '"+apples.get(i).getId()+"'");
            }
            // --------------------- 批量执行
            for (int i = 0; i < 1000; i++) {
                statement1.addBatch(" update mdr_enterprise_info set checked_time = '"+ format+"'"
                        +" where id = '"+apples.get(i).getId()+"'");
            }
            int[] ints = statement1.executeBatch();
            // --------------------- 批量执行


            // 删除
            statement.execute("delete from mdr_enterprise_info where id = 1");

            // 插入
            statement.execute(String.format("insert into mdr_enterprise_info (id,e_name) values(%s,%s)", 1, "2"));


            connection.commit();
            connection.setAutoCommit(autoCommit);
            System.out.println("sql批量更新耗时："+(System.currentTimeMillis() - time3));
            Map<String, Apple> appleMap = apples.stream().
                    collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    protected class Apple{
        private String Id;
        private String name;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Apple(String id, String name) {
            this.Id = id;
            this.name = name;
        }

        public Apple() {

        }
    }
}
