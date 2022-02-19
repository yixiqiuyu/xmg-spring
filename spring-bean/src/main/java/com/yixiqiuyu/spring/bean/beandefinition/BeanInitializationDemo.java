package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.bean.factory.DefaultUserFactory;
import com.yixiqiuyu.spring.bean.factory.IUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yixiqiuyu
 * @Description Bean 初始化 Demo
 * @Date 2022/2/20 0:28
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);

        // 启动Spring上下文
        applicationContext.refresh();

        // 依赖查找IUserFactory
        applicationContext.getBean(DefaultUserFactory.class);

        // 关闭Spring上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    public static IUserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
