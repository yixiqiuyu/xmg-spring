package com.yixiqiuyu.dependency.injection;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author yixiqiuyu
 * @Description 基于java注解的依赖 Constructor 方法注入
 * @Date 2022/3/9 20:40
 */
public class AnnotationDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class(配置类)
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class );
        //applicationContext.register(Config.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载XML资源，解析并生成 BeanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);


        // 启动Spring上下文
        applicationContext.refresh();

        //依赖查找并且创建bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);
        // 关闭Spring应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
