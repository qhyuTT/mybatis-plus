1、首先我们引入spring需要的包
- implementation "${lib.'spring-web'}" AnnotationConfigApplicationContext启动容器
- implementation "${lib.'spring-context-support'}" 其实是为了context的引入，需要compmentScan扫描路径

2、然后我们需要数据库的连接驱动这些信息 毕竟要看的是mybatis plus的源码
-  implementation "${lib.'spring-jdbc'}" jdbc连接数据库，同时还需要mysql的连接驱动
-  implementation "${lib.'mysql'}" 其实项目外层都给你把东西弄好了，直接搬过来用
-  implementation "${lib.'spring-aop'}" jdbc里面包含了tx相关的，但是事务不是核心原理是aop么，加进来以免到时候报错
-  implementation("com.alibaba:druid:1.1.21") 数据库连接池

3、目前我们的基础环境差不多了，可以开始搭建项目
- MybatisQhyuApplicion为我们的项目启动类，后续的一些sql逻辑也会在里面执行
- StartConfig为启动的扫描类
- DataSourceConfig为数据库的配置类，以下是三种常用的组件
  - DataSource是一个接口，用于获取数据库连接，它定义了一些方法，如getConnection()等
  - JdbcTemplate是Spring JDBC框架的核心组件之一，它封装了JDBC操作，如query update batchUpdat执行sql
  - PlatformTransactionManager是spring的事务管理器，它是用于管理事务的接口。
- 因此，DateSource用于获取数据库连接，jdbcTemplate用于执行sql语句并处理结果集，而PlatformTransactionManager用于管理事务。PlatformTransactionManager 是用于管理事务的接口。它定义了一组方法，如 getTransaction()、commit()、rollback() 等，用于管理事务的生命周期

4、到此Spring和数据库相关的东西配置好了，我们需要把mybatis plus引入到项目中。
- 我们知道使用mybatis plus的第一个注解就是@MapperScan，得找到他在那个module下
- 哦，他是mybatis的，所以干脆直接引入mybaits-plus-boot-start应该都有了



