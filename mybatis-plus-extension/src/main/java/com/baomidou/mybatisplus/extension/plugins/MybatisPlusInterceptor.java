/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baomidou.mybatisplus.extension.plugins;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.toolkit.PropertyMapper;
import lombok.Setter;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.*;

/**
 * @author miemie
 * @since 3.4.0
 */
@SuppressWarnings({"rawtypes"})
@Intercepts(
    {
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
        @Signature(type = StatementHandler.class, method = "getBoundSql", args = {}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
    }
)
public class MybatisPlusInterceptor implements Interceptor {

    @Setter
    private List<InnerInterceptor> interceptors = new ArrayList<>();

    /**
     * 在 Mybatis Plus 中，**Executor** 和 **StatementHandler** 是两个重要的概念，它们都用于执行 SQL 语句。但是，它们之间存在一些重要的区别。
     *
     * **Executor** 负责执行 SQL 语句的生命周期管理。它负责创建、准备、执行和关闭与数据库的连接。Executor 还负责处理事务和缓存。
     *
     * **StatementHandler** 负责将 SQL 语句转换为 JDBC 语句并传递给 Executor 执行。它还负责设置 SQL 语句的参数。
     *
     * **Executor 和 StatementHandler 的关系**
     *
     * Executor 和 StatementHandler 之间的关系可以概括为以下几点：
     *
     * * StatementHandler 是 Executor 的一个依赖项。Executor 在执行 SQL 语句之前，需要先从 StatementHandler 获取 SQL 语句和参数。
     * * StatementHandler 的职责是将 SQL 语句转换为 JDBC 语句并传递给 Executor 执行。Executor 负责执行 SQL 语句并将结果返回给应用程序。
     * * Executor 和 StatementHandler 可以相互协作，完成 SQL 语句的执行。
     *
     * **Executor 的类型**
     *
     * Mybatis Plus 提供了三种类型的 Executor：
     *
     * * **SimpleExecutor**：最简单的 Executor，每次执行 SQL 语句都会创建一个新的连接。
     * * **ReuseExecutor**：会缓存连接，并重用已有的连接来执行 SQL 语句。
     * * **BatchExecutor**：用于批量执行 SQL 语句。
     *
     * **StatementHandler 的类型**
     *
     * Mybatis Plus 提供了两种类型的 StatementHandler：
     *
     * * **SimpleStatementHandler**：最简单的 StatementHandler，将 SQL 语句直接转换为 JDBC 语句。
     * * **PreparedStatementHandler**：使用 JDBC 的 PreparedStatement 来执行 SQL 语句，可以提高性能。
     *
     * **总结**
     *
     * Executor 和 StatementHandler 都是 Mybatis Plus 中重要的概念，它们都用于执行 SQL 语句。Executor 负责执行 SQL 语句的生命周期管理，StatementHandler 负责将 SQL 语句转换为 JDBC 语句并传递给 Executor 执行。Executor 和 StatementHandler 可以相互协作，完成 SQL 语句的执行。
     *
     * **以下是一些额外的信息：**
     *
     * * Executor 和 StatementHandler 可以通过配置文件或 Java 代码进行配置。
     * * Mybatis Plus 还提供了一些扩展的 Executor 和 StatementHandler，可以满足更复杂的场景需求。
     *
     * **希望这些信息对您有所帮助。**
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        if (target instanceof Executor) {
            final Executor executor = (Executor) target;
            Object parameter = args[1];
            boolean isUpdate = args.length == 2;
            MappedStatement ms = (MappedStatement) args[0];
            if (!isUpdate && ms.getSqlCommandType() == SqlCommandType.SELECT) {
                RowBounds rowBounds = (RowBounds) args[2];
                ResultHandler resultHandler = (ResultHandler) args[3];
                BoundSql boundSql;
                if (args.length == 4) {
                    boundSql = ms.getBoundSql(parameter);
                } else {
                    // 几乎不可能走进这里面,除非使用Executor的代理对象调用query[args[6]]
                    boundSql = (BoundSql) args[5];
                }
                for (InnerInterceptor query : interceptors) {
                    if (!query.willDoQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql)) {
                        return Collections.emptyList();
                    }
                    // 这里是否确实改了sql，在多租户的模式下。我猜测就是这里加的sql
                    query.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
                }
                CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
                return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
            } else if (isUpdate) {
                for (InnerInterceptor update : interceptors) {
                    if (!update.willDoUpdate(executor, ms, parameter)) {
                        return -1;
                    }
                    update.beforeUpdate(executor, ms, parameter);
                }
            }
        } else {
            // StatementHandler
            final StatementHandler sh = (StatementHandler) target;
            // 目前只有StatementHandler.getBoundSql方法args才为null
            if (null == args) {
                for (InnerInterceptor innerInterceptor : interceptors) {
                    innerInterceptor.beforeGetBoundSql(sh);
                }
            } else {
                Connection connections = (Connection) args[0];
                Integer transactionTimeout = (Integer) args[1];
                for (InnerInterceptor innerInterceptor : interceptors) {
                    // 所以我这里感觉实现这个逻辑也可以打印sql信息
                    innerInterceptor.beforePrepare(sh, connections, transactionTimeout);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor || target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void addInnerInterceptor(InnerInterceptor innerInterceptor) {
        this.interceptors.add(innerInterceptor);
    }

    public List<InnerInterceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

    /**
     * 使用内部规则,拿分页插件举个栗子:
     * <p>
     * - key: "@page" ,value: "com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor"
     * - key: "page:limit" ,value: "100"
     * <p>
     * 解读1: key 以 "@" 开头定义了这是一个需要组装的 `InnerInterceptor`, 以 "page" 结尾表示别名
     * value 是 `InnerInterceptor` 的具体的 class 全名
     * 解读2: key 以上面定义的 "别名 + ':'" 开头指这个 `value` 是定义的该 `InnerInterceptor` 属性需要设置的值
     * <p>
     * 如果这个 `InnerInterceptor` 不需要配置属性也要加别名
     */
    @Override
    public void setProperties(Properties properties) {
        PropertyMapper pm = PropertyMapper.newInstance(properties);
        Map<String, Properties> group = pm.group(StringPool.AT);
        group.forEach((k, v) -> {
            InnerInterceptor innerInterceptor = ClassUtils.newInstance(k);
            innerInterceptor.setProperties(v);
            addInnerInterceptor(innerInterceptor);
        });
    }
}
