package com.yixiqiuyu.dependency.injection;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

/**
 * @Description: {@link org.springframework.beans.factory.ObjectProvider} 实现延迟依赖注入
 * @Author: yixiqiuyu
 * @Date: 2022/3/15  22:58
 */

public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // superUser -> primary=true

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载XML资源，解析并生成 BeanDefinition
        definitionReader.loadBeanDefinitions(resourcePath);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser Bean
        System.out.println("demo.user:" + demo.user);
        System.out.println("demo.userObjectProvider:" + demo.userObjectProvider.getObject());
        System.out.println("demo.userObjectFactory:" + demo.userObjectFactory.getObject());

        demo.userObjectProvider.forEach(System.out::println);


        applicationContext.close();
    }
}
