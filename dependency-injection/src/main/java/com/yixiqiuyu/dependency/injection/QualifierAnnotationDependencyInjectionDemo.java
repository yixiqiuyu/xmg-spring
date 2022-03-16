package com.yixiqiuyu.dependency.injection;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * @Description: {@link org.springframework.beans.factory.annotation.Qualifier} 示例
 * @Author: yixiqiuyu
 * @Date: 2022/3/15  22:58
 */

public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // superUser -> primary=true

    @Autowired
    @Qualifier("user") // 指定Bean名称或ID
    private User namedUser;

//    整体应用上下文存在4个User类型的Bean
//    superUser
//    user
//    user1 -> @Qualifier
//    user2 -> @Qualifier

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;
    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
       return createUser(7L);
    }
    @Bean
    @Qualifier // 进行逻辑分组
    public User user2() {
        return createUser(8L);
    }


    @Bean
    @UserGroup // 分组
    public User user3() {
        return createUser(9L);
    }


    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup.xml";
        // 加载XML资源，解析并生成 BeanDefinition
        definitionReader.loadBeanDefinitions(resourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待输出 superUser Bean
        System.out.println("demo.user:" + demo.user);

        // 期待输出 User Bean
        System.out.println("demo.namedUser:" + demo.namedUser);

        System.out.println("demo.allUsers:" + demo.allUsers);

        System.out.println("demo.qualifierUsers:" + demo.qualifierUsers);

        System.out.println("demo.groupUsers:" + demo.groupUsers);

        applicationContext.close();
    }
}
