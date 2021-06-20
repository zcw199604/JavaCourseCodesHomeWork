package com.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 数据源获取，模拟简单的从库负载均衡
 * @author lw
 */
@Component
public class ManagementCenter {

    @Autowired
    @Qualifier("master")
    DataSource masterDataSource;
    @Autowired
    @Qualifier("slave1")
    DataSource slave1DataSource;
    @Autowired
    @Qualifier("slave2")
    DataSource slave2DataSource;

    int slaveIndex = 1;

    public DataSource getDefaultDataSource() {
        return masterDataSource;
    }

    /**
     * 这里就简单的模拟一下负载均衡
     */
    public DataSource getSlaveDataSource() {
        if (slaveIndex == 1) {
            slaveIndex = 2;
            return slave1DataSource;
        }
        slaveIndex = 1;
        return slave2DataSource;
    }
}
