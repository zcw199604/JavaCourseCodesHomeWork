package io.kimmking.cache;

import com.alibaba.fastjson.JSON;
import io.kimmking.cache.cluster.ClusterJedis;
import io.kimmking.cache.sentinel.SentinelJedis;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.protocol.RedisCommand;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "io.kimmking.cache")
public class RedisApplication {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public static void main(String[] args) {

		RedisApplication redisApplication = new RedisApplication();

//		redisApplication.jedisClient();
//		redisApplication.jedisSentinel();
//		redisApplication.jedisSentinelClient();

		// 作业：
		// 1. 参考C2，实现基于Lettuce和Redission的Sentinel配置
//		redisApplication.lettuceClient();
//		redisApplication.lettuceSentinel();

//		redisApplication.redissionClient();
//		redisApplication.redissionSentinel();

		// 2. 实现springboot/spring data redis的sentinel配置
//		SpringApplication.run(RedisApplication.class, args);
//		redisApplication.springRedisSentinel();

		// 3. 使用jedis命令，使用java代码手动切换 redis 主从

//		Jedis jedis1 = new Jedis("localhost", 6381);
//		System.out.println(jedis1.info());
//		jedis1.set("test", "test");
//		System.out.println(jedis1.get("test"));
//
//		Jedis jedis2 = new Jedis("localhost", 6381);
//		jedis2.slaveof("localhost", 6383);
//		System.out.println(jedis2.info());
//		System.out.println(jedis2.get("test"));

		// 4. 使用C3的方式，使用java代码手动操作sentinel
//		Jedis jedis = new Jedis("localhost", 16381);
//		List<Map<String, String>> masters = jedis.sentinelMasters();
//		System.out.println(JSON.toJSONString(masters));

		// C4. Redis Cluster
		// 作业：
		// 5.使用命令行配置Redis cluster:
		//  1) 以cluster方式启动redis-server
		//  2) 用meet，添加cluster节点，确认集群节点数目
		//  3) 分配槽位，确认分配成功
		//  4) 测试简单的get/set是否成功
		// 然后运行如下代码
// 		JedisCluster cluster = ClusterJedis.getJedisCluster();
//		for (int i = 0; i < 100; i++) {
//			cluster.set("cluster:" + i, "data:" + i);
//		}
//		System.out.println(cluster.get("cluster:92"));
//		ClusterJedis.close();

		//SpringApplication.run(RedisApplication.class, args);

	}

	private void jedisClient() {
		// C1.最简单demo
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println(jedis.info());
		jedis.set("uptime", new Long(System.currentTimeMillis()).toString());
		System.out.println(jedis.get("uptime"));
	}

	private void jedisSentinel() {
		// C2.基于sentinel和连接池的demo
		Jedis sjedis = SentinelJedis.getJedis();
		System.out.println(sjedis.info());
		sjedis.set("uptime2", new Long(System.currentTimeMillis()).toString());
		System.out.println(sjedis.get("uptime2"));
		SentinelJedis.close();
	}

	private void jedisSentinelClient() {
		// C3. 直接连接sentinel进行操作
		Jedis jedisSentinel = new Jedis("localhost", 16381); // 连接到sentinel
		List<Map<String, String>> masters = jedisSentinel.sentinelMasters();
		System.out.println(JSON.toJSONString(masters));
		List<Map<String, String>> slaves = jedisSentinel.sentinelSlaves("mymaster");
		System.out.println(JSON.toJSONString(slaves));
	}

	private void lettuceClient() {
		RedisClient redisClient = RedisClient.create("redis://192.168.101.104:6381/");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		System.out.println(connection.toString());

		RedisCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("key", "Hello, Lettuce Redis");
		System.out.println(syncCommands.get("key"));
	}

	private void lettuceSentinel() {
		RedisURI redisURI = RedisURI.Builder
				.sentinel("192.168.101.104", 16381)
                .withSentinelMasterId("mymaster")
				.build();
		RedisClient client = RedisClient.create(redisURI);
		StatefulRedisConnection<String, String> connection = client.connect();
		System.out.println(connection.toString());

		RedisCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("key", "Hello, Lettuce Redis");
		System.out.println(syncCommands.get("key"));
	}

	private void redissionClient() {
		Config config = new Config();
		config.useSingleServer()
				.setAddress("redis://192.168.101.104:6379");
		RedissonClient client = Redisson.create(config);
		RMap<String, String> map = client.getMap("map");
		map.put("test", "test");
		System.out.println(map.toString());
	}

	private void redissionSentinel() {
		Config config = new Config();
		config.useSentinelServers().addSentinelAddress("redis://192.168.101.104:16379");
		RedissonClient client = Redisson.create(config);
		RMap<String, String> map = client.getMap("map");
		map.put("test", "test");
		System.out.println(map.toString());
	}

	private void springRedisSentinel() {
		redisTemplate.opsForValue().set("test", "test");
		System.out.println(redisTemplate.opsForValue().get("test"));
	}

}
