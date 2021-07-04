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

package com.account.demo.mapper;

import com.account.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author zcw
 */
@Mapper
@Repository
public interface AccountMapper {

    /**
     * pay for money
     * @param account account
     */
    @Update("update `himly_dubbo_account` set us_wallet = us_wallet + #{us_wallet}, cny_wallet = cny_wallet + " +
            "#{cny_wallet} where us_wallet >= #{us_wallet} and cny_wallet >= #{cny_wallet} and id = #{id}")
    boolean payment(Account account);

    /**
     * query one
     * @param account account
     * @return account
     */
    @Select("select * from himly_dubbo_account where id = #{id}")
    Account queryOne(Account account);
}
