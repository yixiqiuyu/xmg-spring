package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.bean.factory.DefaultUserFactory;
import com.yixiqiuyu.spring.bean.factory.IUserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: 单体bean 注册实例
 * @Author: yixiqiuyu
 * @Date: 2022/2/27  23:18
 */

public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 创建一个外部对象
        IUserFactory userFactory = new DefaultUserFactory();
        // 注册外部单例对象
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        beanFactory.registerSingleton("userFactory", userFactory);

        IUserFactory userFactoryByLookup = beanFactory.getBean("userFactory", IUserFactory.class);

        System.out.println("userFactory == userFactoryByLookup:" + (userFactory == userFactoryByLookup));
        //applicationContext.register(SingletonBeanRegistrationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();

    }
}
