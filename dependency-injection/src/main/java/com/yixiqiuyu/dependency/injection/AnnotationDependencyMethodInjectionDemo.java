package com.yixiqiuyu.dependency.injection;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author yixiqiuyu
 * @Description 基于java注解的依赖方法注入
 * @Date 2022/3/9 20:40
 */
public class AnnotationDependencyMethodInjectionDemo {


    UserHolder userHolder;

    UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void intiUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class(配置类)
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class );
        //applicationContext.register(Config.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载XML资源，解析并生成 BeanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(resourcePath);


        // 启动Spring上下文
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        UserHolder userHolder = demo.userHolder;

        //依赖查找并且创建bean
        //UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        System.out.println(demo.userHolder2);
        System.out.println(userHolder == demo.userHolder2);
        // 关闭Spring应用上下文
        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
