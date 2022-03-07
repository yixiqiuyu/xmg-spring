package com.yixiqiuyu.spring.dependency.lookup;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @author yixiqiuyu
 * @Description {@link BeanInitializationException} 示例
 * @Date 2022/3/8 0:19
 */
public class BeanInitializationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册BeanDefinition Bean Class是一个CharSequence 接口
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Character.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动应用上下文
        applicationContext.refresh();


        applicationContext.close();
    }
}
