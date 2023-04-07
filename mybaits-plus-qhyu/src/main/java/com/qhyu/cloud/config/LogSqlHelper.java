package com.qhyu.cloud.config;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：LogSqlHelper <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 04月07日 10:53 <br>
 * @version v1.0 <br>
 */
public class LogSqlHelper {

    private static final Logger log = LoggerFactory.getLogger(LogSqlHelper.class);

    private static final String SELECT = "select";

    private static final String FROM = "from";

    private static final String SIMPLE_SELECT = "select * ";

    private static final int MAX_SQL_LENGTH = 120;

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public LogSqlHelper() {
    }

    public static Object intercept(Invocation invocation, int slowSqlThreshold, boolean optimizeSql) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = invocation.proceed();
        long cost = System.currentTimeMillis() - startTime;
        if (cost >= (long) slowSqlThreshold) {
            log.info("cost = {} ms, affected rows = {}; \n SQL: {}",
                cost, formatResult(returnValue), formatSql(invocation, optimizeSql));
        }
        return returnValue;
    }

    private static Object formatResult(Object obj) {
        if (obj == null) {
            return "NULL";
        } else if (obj instanceof List) {
            return ((List) obj).size();
        } else if (!(obj instanceof Number) && !(obj instanceof Boolean) && !(obj instanceof Date)
            && !(obj instanceof String)) {
            return obj instanceof Map ? ((Map) obj).size() : 1;
        } else {
            return obj;
        }
    }

    private static String formatSql(Invocation invocation, boolean isOptimizeSql) {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        String formatSql = sql.toLowerCase();
        if (isOptimizeSql && formatSql.startsWith(SELECT) && formatSql.length() > MAX_SQL_LENGTH) {
            sql = SIMPLE_SELECT + sql.substring(formatSql.indexOf(FROM));
        }

        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", formatParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    Object obj;
                    if (metaObject.hasGetter(propertyName)) {
                        obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", formatParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", formatParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private static String formatParameterValue(Object obj) {
        if (obj == null) {
            return "NULL";
        } else {
            String value = obj.toString();
            if (obj instanceof Date) {
                DateFormat dateFormat = new SimpleDateFormat(PATTERN);
                value = dateFormat.format((Date) obj);
            }
            if (!(obj instanceof Number) && !(obj instanceof Boolean)) {
                value = "'" + value + "'";
            }
            return value;
        }
    }
}

