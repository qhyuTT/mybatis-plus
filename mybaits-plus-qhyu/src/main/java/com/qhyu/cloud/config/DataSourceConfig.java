package com.qhyu.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：DataSourceConfig <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 10:46 <br>
 * @version v1.0 <br>
 */
@EnableTransactionManagement
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        // 使用阿里的连接池创建数据连接
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("skyworth");
        druidDataSource.setPassword("SkyWorth#2020");
        druidDataSource.setUrl("jdbc:mysql://192.168.17.103:3306/SkyWorth?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(10);
        return druidDataSource;
    }

    // 执行sql的模版类
    /*@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }*/

    // 事务管理器，也就是说事物的提交这些都是他去管理的，我们可以测试一个
   /* @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }*/
}
