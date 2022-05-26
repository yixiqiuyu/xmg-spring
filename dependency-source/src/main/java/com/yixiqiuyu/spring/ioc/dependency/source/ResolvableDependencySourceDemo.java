package com.yixiqiuyu.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author yixiqiuyu
 * @Description  ResolvableDependency作为依赖来源
 * @Date 2022/5/19 21:27
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        applicationContext.register(ResolvableDependencySourceDemo.class);


        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "Hello World");
        });
        // 启动spring应用上下文
        applicationContext.refresh();



        /*AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
            configurableListableBeanFactory.registerResolvableDependency(String.class, "HelloWorld");
        }*/



        applicationContext.close();
    }
}
