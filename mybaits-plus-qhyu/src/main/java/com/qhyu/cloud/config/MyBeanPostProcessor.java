package com.qhyu.cloud.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * All rights Reserved, Designed By http://xnky.travelsky.net/ <br>
 * Title：MyBeanPostProcessor <br>
 * Package：com.qhyu.cloud.config <br>
 * Copyright © 2023 xnky.travelsky.net Inc. All rights reserved. <br>
 * Company：Aviation Cares Of Southwest Chen Du LTD  <br>
 * @author 于琦海 <br>
 * date 2023年 03月31日 14:30 <br>
 * @version v1.0 <br>
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       if (beanName.equals("userInfoMapper")){
           System.out.println(bean.toString());
       }
       return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userInfoMapper")){
            System.out.println(bean.toString());
        }
        return bean;
    }
}
