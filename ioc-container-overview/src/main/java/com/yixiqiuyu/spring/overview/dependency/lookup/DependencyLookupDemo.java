package com.yixiqiuyu.spring.overview.dependency.lookup;

import com.yixiqiuyu.spring.overview.annotation.Super;
import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Description: 依赖查找示例
 * 1.通过名称来查找
 * 2.通过类型来查找
 * 3.通过注解来查找
 * @Author: thinkpad
 * @Date: 2022/2/9  20:17
 */

public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置xml配置文件
        // 启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup.xml");

        // 通过类型的方式来查找
        lookupInType(beanFactory);

        //通过类型查找
        lookupByCollectionType(beanFactory);

        // 通过注解查找
        lookupByAnnotationType(beanFactory);
        // 通过名称的方式查找
//        lookupInRealTime(beanFactory);
//        lookupInLazy(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super所有的 User 集合对象：" + users);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象：" + users);
        }
    }

    private static void lookupInType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找：" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
       ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }
}
