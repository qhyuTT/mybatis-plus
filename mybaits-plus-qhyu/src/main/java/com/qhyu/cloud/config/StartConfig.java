package com.qhyu.cloud.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：StartConfig <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * @date 2023年 03月24日 10:37 <br>
 * @version v1.0 <br>
 */
@ComponentScan("com.qhyu.cloud")
@MapperScan(value = "com.qhyu.cloud.mapper.**")
// spring会把META-INFO中的东西扫起来，注入到容器，我们用的spring，所以手动import进来
@Import(MybatisPlusAutoConfiguration.class)
public class StartConfig {
    /**
     * 在MapperScan里面没有找到xml的解析逻辑呢，好像是扫描路径把mapper接口让spring registry管理
     *
     */
    //@Bean
    public LogQueryAndUpdateSqlHandler1 getLogSqlHandler1() {
        return new LogQueryAndUpdateSqlHandler1(true);
    }
    //@Bean
    public LogQueryAndUpdateSqlHandler getLogSqlHandler() {
        return new LogQueryAndUpdateSqlHandler(true);
    }

   // @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
