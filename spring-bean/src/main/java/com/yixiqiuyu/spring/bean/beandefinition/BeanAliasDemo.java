package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: Bean别名实例
 * @Author: thinkpad
 * @Date: 2022/2/15  21:39
 */

public class BeanAliasDemo {
    public static void main(String[] args) {
        // 配置xml配置文件
        // 启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        // 通过别名yixiqiuyu-user别名获取曾用名 user
        User yixiqiuyuUser = beanFactory.getBean("yixiqiuyu-user", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("yixiqiuyu-user 是否和 user 相同：" + (yixiqiuyuUser == user));

    }
}
