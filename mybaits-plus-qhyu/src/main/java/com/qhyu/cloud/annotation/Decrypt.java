package com.qhyu.cloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Decrypt {
    // 这个是解密注解，用于filed上，也就是查询出来的数据进行解密。

    // 实现步骤：
    // 1）按照我的理解，首先这是作用于查询的，查询又有分页和普通查询，所以这点不知道有没有区别。
    // 2）我首先会认为我的查询要先走MybatisPlusInterceptor，然后走自定义的DecryptInterceptor。
    // 3）
    // 4）
    // 5）
    // 6）
}
