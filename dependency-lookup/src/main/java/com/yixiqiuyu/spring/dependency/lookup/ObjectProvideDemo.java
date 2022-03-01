package com.yixiqiuyu.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @Description: 通过 {@link ObjectProvider}进行依赖查找
 * @Author: yixiqiuyu
 * @Date: 2022/2/28  22:59
 */

public class ObjectProvideDemo {// @Configuration 是非必须注解
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //
        applicationContext.register(ObjectProvideDemo.class);

        // 启动应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByObjectProvider(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    // 方法名就是bean名称 = "helloWorld"
    @Bean
    public String helloWorld() {
        return "Hello World";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
       /* for (String s : applicationContext.getBeanProvider(String.class)) {
            System.out.println(s);
        }*/
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
