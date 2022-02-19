package com.yixiqiuyu.spring.overview.container;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @Description: Ioc 容器示例
 * @Author: thinkpad
 * @Date: 2022/2/13  15:03
 */

public class BeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //加载配置
        String location = "META-INF/dependency-lookup.xml";
        int i = reader.loadBeanDefinitions(location);
        System.out.println("获取到bean的个数：" + i);

        lookupByCollectionType(beanFactory);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象：" + users);
        }
    }
}
