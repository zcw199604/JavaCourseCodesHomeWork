# Week08
***
## 作业说明
*设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表并在新结构在演示常见的增删改查操作*

### 环境配置
#### 设置MySQL
&ensp;&ensp;&ensp;&ensp;使用docker设置mysql

```shell script
# 启动两个mysql
docker run --name mysql11 -p 3309:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ROOT_HOST=% -d mysql:latest
docker run --name mysql12 -p 3310:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ROOT_HOST=% -d mysql:latest

# 在11上创建数据库demo_ds_0
docker exec -ti mysql11 mysql -u root -p
create database demo_ds_0;

# 在12上创建数据库demo_ds_1
docker exec -ti mysql11 mysql -u root -p
create database demo_ds_1;
```

#### ShardingSphere Proxy 5.0.0 alpha 设置
&ensp;&ensp;&ensp;&ensp;docker一直不能设置成功，有点奇怪，后来换成4.1.1

&ensp;&ensp;&ensp;&ensp;下面需要配置两个文件：server.yaml、config-sharding.yaml,示例如下（配置都有默认示例说明的，相应进行修改即可）

&ensp;&ensp;&ensp;&ensp;server.yaml

```yaml
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

######################################################################################################
# 
# If you want to configure governance, authorization and proxy properties, please refer to this file.
# 
######################################################################################################
#
#governance:
#  name: governance_ds
#  registryCenter:
#    type: ZooKeeper
#    serverLists: localhost:2181
#    props:
#      retryIntervalMilliseconds: 500
#      timeToLiveSeconds: 60
#      maxRetries: 3
#      operationTimeoutMilliseconds: 500
#  overwrite: false

authentication:
  users:
    root:
      password: root
    sharding:
      password: sharding
      authorizedSchemas: sharding_db

props:
  max-connections-size-per-query: 1
  acceptor-size: 16  # The default value is available processors count * 2.
  executor-size: 16  # Infinite by default.
  proxy-frontend-flush-threshold: 128  # The default value is 128.
    # LOCAL: Proxy will run with LOCAL transaction.
    # XA: Proxy will run with XA transaction.
    # BASE: Proxy will run with B.A.S.E transaction.
  proxy-transaction-type: LOCAL
  proxy-opentracing-enabled: false
  proxy-hint-enabled: false
  query-with-cipher-column: true
  sql-show: true
  check-table-metadata-enabled: false
```

&ensp;&ensp;&ensp;&ensp;config-sharding.yaml

```yaml
######################################################################################################
#
# If you want to connect to MySQL, you should manually copy MySQL driver to lib directory.
#
######################################################################################################
schemaName: sharding_db
dataSources:
  ds0:
    url: jdbc:mysql://82.156.12.100:3309/demo_ds_0
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 65
  ds1:
    url: jdbc:mysql://82.156.12.100:3310/demo_ds_1
    username: root
    password: 123456
    connectionTimeoutMilliseconds: 30000
    idleTimeoutMilliseconds: 60000
    maxLifetimeMilliseconds: 1800000
    maxPoolSize: 65

shardingRule:
  tables:
    t_order:
      actualDataNodes: ds${0..1}.t_order_${0..15}
      databaseStrategy:
        inline:
          shardingColumn: user_id
          algorithmExpression: ds${user_id % 2}
      tableStrategy:
        inline:
          shardingColumn: order_id
          algorithmExpression: t_order_${order_id % 16}
      keyGenerator:
        type: SNOWFLAKE
        column: order_id
  bindingTables:
    - t_order
  defaultTableStrategy:
    none:
```

&ensp;&ensp;&ensp;&ensp;使用mysql命令或者mysqlworkbench连接上sharding，运行下面的SQL语句生成测试的表，运行成功看到日志中一大批SQL语句，

```sql
CREATE TABLE IF NOT EXISTS `t_order` (
    `order_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
```

### SpringBoot Mybatis配置
&ensp;&ensp;&ensp;&ensp;需要修改数据库连接配置，大致如下：

```properties
# mybatis的config文件位置配置
mybatis.config-location=classpath:mybatis/mybatis-config.xml
# 各个表的xml文件位置配置
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
mybatis.type-aliases-package=com.neo.model

# 数据库连接信息配置，自行更换数据库，用户名和密码,配置为ShardingSphereProxy
spring.datasource.url=jdbc:mysql://localhost:13308/sharding_db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8\
  &useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```


&ensp;&ensp;&ensp;&ensp;测试类进行测试，代码如下：

```java
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

```




