package com.yixiqiuyu.spring.ioc.bean.lifecycle;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @author yixiqiuyu
 * @Description 注解BeanDefinition 解析示例
 * @Date 2022/5/26 22:08
 */
public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于Java注解的 AnnotatedBeanDefinitionReader的实现
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionNumBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类(非@Component class)
        annotatedBeanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class, User.class);
        int beanDefinitionNumAfter = beanFactory.getBeanDefinitionCount();
        int beanNumbers = beanDefinitionNumAfter - beanDefinitionNumBefore;
        System.out.println("已加载BeanDefinition数量：" + beanNumbers);

        // 普通的Class作为Component 注册到Spring IOC容器中后，通常Bean名称为首字母小写
        // Bean 名称生成来自于BeanNameGenerator，注解实现AnnotationBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

    }
}
