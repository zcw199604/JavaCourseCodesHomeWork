package com.zcw.mappers;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.zcw.models.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@MapperScan("week0802.week0802.mappers")
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 通过不同的查询条件的传入，可以体会到分库分表是需要设计的
     * 一个设计不好，查询难搞
     */
    @Test
    public void test() throws SQLException {
        // 通过sharding插入数据，通过sharding自己的日志输出看出插入不同的数据库和表
        //orderMapper.insertOne(new Order(3L, 3L));
        //orderMapper.insertOne(new Order(4L, 4L));

        // 只传user_id，看到单库进行了所有表的查询
        Map<String, Object> condition = new HashMap<>(1);
        condition.put("user_id", 3L);

        List<Map<String, Object>> orderQuery = orderMapper.query(condition);
        assert orderQuery.size() == 1;
        for (Map item: orderQuery) {
            System.out.println(item.toString());
        }

        // 只传order_id，看到进行了多库单表的查询
        condition = new HashMap<>(1);
        condition.put("order_id", 1L);
        orderQuery = orderMapper.query(condition);
        assert orderQuery.size() == 1;
        for (Map item: orderQuery) {
            System.out.println(item.toString());
        }

        // 传入order_id和user_id，看到进行单库单表的查询
        condition = new HashMap<>(2);
        condition.put("order_id", 2L);
        condition.put("user_id", 2L);
        orderQuery = orderMapper.query(condition);
        assert orderQuery.size() == 1;
        for (Map item: orderQuery) {
            System.out.println(item.toString());
        }
    }
}
