package com.yixiqiuyu.spring.ioc.bean.lifecycle;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author yixiqiuyu
 * @Description Bean 元信息配置示例
 * @Date 2022/5/26 20:56
 */
public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 实例化基于 Properties 资源的 PropertiesBeanDefinitionReader
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        String location = "/META-INF/user.properties";
        // 基于ClassPath 加载 properties 资源
        ClassPathResource resource = new ClassPathResource(location);
        // 指定字符编码集 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        int beanNumbers = propertiesBeanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载BeanDefinition数量：" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);


        User user1 = new User();
        user1.setName("葛建");
        System.out.println(user1);
    }
}
