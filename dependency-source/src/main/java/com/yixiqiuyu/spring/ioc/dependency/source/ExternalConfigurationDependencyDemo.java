package com.yixiqiuyu.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author yixiqiuyu
 * @Description
 * @Date 2022/5/19 21:52
 */
@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties", encoding = "GBK")
public class ExternalConfigurationDependencyDemo {

    @Value("${user.id:2}")
    private Integer id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath:/META-INF/default.properties}")
    private String resource;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        applicationContext.register(ExternalConfigurationDependencyDemo.class);

        // 启动spring应用上下文
        applicationContext.refresh();

        ExternalConfigurationDependencyDemo demo = applicationContext.getBean(ExternalConfigurationDependencyDemo.class);
        System.out.println("demo.id: " + demo.id);
        System.out.println("demo.name: " + demo.name);
        System.out.println("demo.resource: " + demo.resource);

        Iterator<String> iterator = Arrays.stream(applicationContext.getBeanDefinitionNames()).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("===============");
        // System.out.println(applicationContext.getApplicationName());

        applicationContext.close();
    }
}
