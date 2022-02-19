package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: Bean 实例化示例
 * @Author: yixiqiuyu
 * @Date: 2022/2/19  21:02
 */

public class BeanInstantiationDemo {
    public static void main(String[] args) {
        // 配置xml配置文件
        // 创建Spring上下文
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = applicationContext.getBean("user-by-static-method", User.class);
        /*user.setId(1L);
        user.setName("zhang");*/
        System.out.println(user);

        User userByInstanceMethod = applicationContext.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        System.out.println(user == userByInstanceMethod);

        /*User user1 = applicationContext.getBean("user-by-static-method", User.class);
        System.out.println(user1);*/

    }
}
