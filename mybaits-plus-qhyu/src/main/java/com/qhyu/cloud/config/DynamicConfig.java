package com.qhyu.cloud.config;

import com.qhyu.cloud.dynamic.DynamicInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Configuration
public class DynamicConfig {

    @Bean
    public DynamicInterface dynamicInterface(){
       return (DynamicInterface)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{DynamicInterface.class},new DynamicInterceptor());
    }
    private class DynamicInterceptor implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(args);
        }
    }
}
