package com.yixiqiuyu.spring.overview.dependency.injection;

import com.yixiqiuyu.spring.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Description: 依赖注入示例
 * @Author: thinkpad
 * @Date: 2022/2/9  23:20
 */

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置xml配置文件
        // 启动Spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection.xml");

        // 依赖来源一：自定义Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 依赖来源二：依赖注入(内建依赖)
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject());

        System.out.println(userObjectFactory.getObject().equals(beanFactory));

        // 依赖来源三：容器内建Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
        // 依赖查找
        // System.out.println(beanFactory.getBean(BeanFactory.class));


        //System.out.println(userRepository.getBeanFactory() == beanFactory);
    }

    public static void whoIsIocContainer(UserRepository userRepository,BeanFactory beanFactory) {

        // BeanFactory -> ApplicationContext -> ConfigurableApplicationContext

        // ConfigurableApplicationContext#getBeanFactory()

        // false
        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }


}
