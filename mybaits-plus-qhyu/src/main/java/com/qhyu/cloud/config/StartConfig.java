package com.qhyu.cloud.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：StartConfig <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月24日 10:37 <br>
 * @version v1.0 <br>
 */
@ComponentScan("com.qhyu.cloud")
@MapperScan("com.qhyu.cloud.mapper")
// spring会把META-INFO中的东西扫起来，注入到容器，我们用的spring，所以手动import进来
@Import(MybatisPlusAutoConfiguration.class)
public class StartConfig {
}
