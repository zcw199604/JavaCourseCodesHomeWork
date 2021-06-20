package com;

import com.zaxxer.hikari.HikariDataSource;
import groovy.util.logging.Slf4j;
import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Component
public class ShardingMasterSlaveDataSource {

    private static Logger log = LoggerFactory.getLogger(ShardingMasterSlaveDataSource.class);

    private final String DRIVER = ".driver-class-name";
    private final String URL = ".url";
    private final String USERNAME = ".username";
    private final String PASSWORD = ".password";
    private final String DBS = "sharding.jdbc.datasource.names";

    @Autowired
    private Environment environment;

    public DataSource createDataSource() throws SQLException {
        // 获取数据库列表
        String[] dbs = Objects.requireNonNull(environment.getProperty(DBS)).split(",");
        log.info("DBS::" + Arrays.toString(dbs));

        // 设置主从，约定第一个为主，其他为从
        MasterSlaveRuleConfiguration configuration = new MasterSlaveRuleConfiguration(dbs[0], dbs[0],
                Arrays.asList(Arrays.copyOfRange(dbs, 1, dbs.length)));
        log.info("ShardingMasterSlaveDataSource master :: " + configuration.getMasterDataSourceName());
        log.info("ShardingMasterSlaveDataSource slave :: " + configuration.getSlaveDataSourceNames());

        // 设置打印SQL语句，查看主从配置和切换是否成功
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(dbs), configuration, new HashMap<>(0),
                properties);
    }

    /**
     * 返回DataSource列表
     */
    private Map<String, DataSource> createDataSourceMap(String[] dbs) {
        Map<String, DataSource> result = new HashMap<>(dbs.length);
        for (String db : dbs) {
            log.info("Create data source ::" + db);
            result.put(db, createDataSource("sharding.jdbc.datasource.ds-" + db));
        }
        return result;
    }

    private DataSource createDataSource(String prefix) {
        log.info(DRIVER + "::" + environment.getProperty(prefix + DRIVER));
        log.info(URL + "::" + environment.getProperty(prefix + URL));
        log.info(USERNAME + "::" + environment.getProperty(prefix + USERNAME));
        log.info(PASSWORD + "::" + environment.getProperty(prefix + PASSWORD));

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(environment.getProperty(prefix + DRIVER));
        dataSource.setJdbcUrl(environment.getProperty(prefix + URL));
        dataSource.setUsername(environment.getProperty(prefix + USERNAME));
        dataSource.setPassword(environment.getProperty(prefix + PASSWORD));
        return dataSource;
    }
}
