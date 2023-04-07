package com.qhyu.cloud.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Properties;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：LogQueryAndUpdateSqlHandler <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 04月07日 10:54 <br>
 * @version v1.0 <br>
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogQueryAndUpdateSqlHandler1 implements Interceptor {
    private int slowSqlThreshold;
    private boolean isOptimizeSql;

    public LogQueryAndUpdateSqlHandler1() {
    }

    public LogQueryAndUpdateSqlHandler1(int slowSqlThreshold) {
        this.slowSqlThreshold = slowSqlThreshold;
    }

    public LogQueryAndUpdateSqlHandler1(boolean isOptimizeSql) {
        this.isOptimizeSql = isOptimizeSql;
    }

    public LogQueryAndUpdateSqlHandler1(int slowSqlThreshold, boolean isOptimizeSql) {
        this.slowSqlThreshold = slowSqlThreshold;
        this.isOptimizeSql = isOptimizeSql;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return LogSqlHelper.intercept(invocation, this.slowSqlThreshold, this.isOptimizeSql);
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
