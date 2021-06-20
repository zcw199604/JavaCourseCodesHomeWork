package com.example.week_07;

import com.ShardingMasterSlaveDataSource;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ShardingMasterSlaveDataSourceTest {
    private static Logger log = LoggerFactory.getLogger(ShardingMasterSlaveDataSourceTest.class);

    @Autowired
    ShardingMasterSlaveDataSource shardingMasterSlaveDataSource;

    /**
     * 运行后从日志可以看出主从之间的切换和从库负载均衡
     */
    @Test
    @Transactional
    public void test() throws SQLException {
        DataSource dataSource = shardingMasterSlaveDataSource.createDataSource();
        log.info("ShardingMasterSlaveDataSource info::" + dataSource.getConnection().getMetaData().getURL());

        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();

        String sql = "select * from stores limit 5";
        statement.execute(sql);
        statement.execute(sql);

        sql = "insert into stores (name, description) VALUES (\"name103\", \"description103\");";
        statement.execute(sql);

        sql = "select * from stores limit 5";
        statement.execute(sql);
    }
}