/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.redis.demo.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * Redis 分布式锁
 *
 * @author zcw
 */
public class RedisLock {

    private enum EnumSingleton {
        /**
         * 懒汉枚举单例
         */
        INSTANCE;
        private RedisLock instance;

        EnumSingleton(){
            instance = new RedisLock();
        }
        public RedisLock getSingleton(){
            return instance;
        }
    }

    public static RedisLock getInstance(){
        return EnumSingleton.INSTANCE.getSingleton();
    }

    private JedisPool jedisPool = new JedisPool();

    /**
     * 进行加锁
     * @param lockValue lock value
     * @param seconds expire time
     * @return get lock
     */
    public boolean lock(String lockValue, int seconds) {
        try(Jedis jedis = jedisPool.getResource()) {
            return "OK".equals(jedis.set(lockValue, lockValue, "NX", "EX", seconds));
        }
    }

    /**
     * 释放锁
     * @param lock lock value
     * @return release lock
     */
    public boolean release(String lock) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " + "return redis.call('del',KEYS[1]) else return 0 end";
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.eval(luaScript, Collections.singletonList(lock), Collections.singletonList(lock)).equals(1L);
        }
    }
}
