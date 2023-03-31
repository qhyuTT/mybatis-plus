package com.qhyu.cloud.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：ApplicationAware <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月31日 10:54 <br>
 * @version v1.0 <br>
 */
@Component
public class ApplicationAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(Class clazz){
        return applicationContext.getBean(clazz);
    }

    public static Object getbean(String name){
        return applicationContext.getBean(name);
    }
}
