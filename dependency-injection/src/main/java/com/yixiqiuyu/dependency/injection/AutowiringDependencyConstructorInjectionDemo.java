package com.yixiqiuyu.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author yixiqiuyu
 * @Description 基于XML资源的依赖 Constructor 方法注入
 * @Date 2022/3/9 20:40
 */
public class AutowiringDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

        String resourcePath = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
        // 加载XML资源，解析并生成 BeanDefinition
        definitionReader.loadBeanDefinitions(resourcePath);

        // 依赖查找并创建Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);
    }
}
