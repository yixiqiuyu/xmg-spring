package com.yixiqiuyu.spring.ioc.bean.lifecycle;

import com.yixiqiuyu.spring.overview.domain.SuperUser;
import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * @author yixiqiuyu
 * @Description BeanDefinition 合并示例
 * @Date 2022/6/10 23:26
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 基于XML资源BeanDefinitionReader实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 基于Classpath 加载xml资源
        String location = "META-INF/dependency-lookup.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        // 通过Bean Id和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        BeanDefinition superUser1 = beanFactory.getMergedBeanDefinition("superUser");

        System.out.println(superUser1.getBeanClassName());
    }
}
