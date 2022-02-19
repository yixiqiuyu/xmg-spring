package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.bean.factory.DefaultUserFactory;
import com.yixiqiuyu.spring.bean.factory.IUserFactory;
import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Description: 特殊的 Bean 实例化示例
 * @Author: yixiqiuyu
 * @Date: 2022/2/19  21:02
 */

public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        // 配置xml配置文件
        // 创建Spring上下文
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        // 通过 ServiceLoader 来创建Bean
        //ServiceLoader<IUserFactory> serviceLoader = applicationContext.getBean("userFactoryServiceLoader",ServiceLoader.class);
        //displayServiceLoader(serviceLoader);
       // demoServiceLoader();

        // 通过 AutowireCapableBeanFactory 来创建Bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        // 创建IUserFactory对象 通过 AutowireCapableBeanFactory实现
        IUserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());


    }

    /*public static void demoServiceLoader() {
        ServiceLoader<IUserFactory> serviceLoader = ServiceLoader.load(IUserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }*/

    private static void displayServiceLoader(ServiceLoader<IUserFactory> serviceLoader) {
        Iterator<IUserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            IUserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }
}
