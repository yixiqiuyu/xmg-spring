package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @Description: 注解 BeanDefinition 示例
 * @Author: yixiqiuyu
 * @Date: 2022/2/15  21:52
 */
@Import(AnnotationBeanDefinitionDemo.Config.class) // 3.通过Import进行导入
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class(配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class );
        //applicationContext.register(Config.class);

        registerUserBeanDefinition(applicationContext,"gejian-user");

        registerUserBeanDefinition(applicationContext);
        // 启动Spring上下文
        applicationContext.refresh();
        // 1.通过@Bean的方式定义
        // 2.通过@Component的方式定义
        // 3.通过@Import方式进行导入


        // 按照类型依赖查找
        System.out.println("Config类型的所有beans：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的所有beans：" + applicationContext.getBeansOfType(User.class));


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id",1L)
                .addPropertyValue("name","yixiqiuyu");
        // 判断如果beanName存在时
        if (StringUtils.hasText(beanName)) {
            // 注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 非命名bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
            //registerUserBeanDefinition(registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }


    @Component // 2.定义当前类作为Spring Bean（组件）
    public static class Config {

        /**
         * 通过Java注解的方式 定义了一个bean
         * @return
         */
        @Bean(name = {"user","yixiqiuyu-user"})
        public User user() {
            User user = new User();
            user.setName("zhangsan");
            user.setId(1L);
            return user;
        }
    }
}
