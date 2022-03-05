package com.yixiqiuyu.spring.dependency.lookup;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

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

        lookupIfAvailable(applicationContext);

        lookupByStreamOps(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
        /*Iterable<String> stringIterable = stringObjectProvider;
        for (String s : stringIterable) {
            System.out.println(s);
        }*/
        stringObjectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(() -> User.createUser());
        System.out.println("当前 User 对象：" + user);
    }

    // 方法名就是bean名称 = "helloWorld"
    @Bean
    @Primary
    public String helloWorld() {
        return "Hello World";
    }

    @Bean
    public String message() {
        return "Message";
    }
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
       /* for (String s : applicationContext.getBeanProvider(String.class)) {
            System.out.println(s);
        }*/
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
