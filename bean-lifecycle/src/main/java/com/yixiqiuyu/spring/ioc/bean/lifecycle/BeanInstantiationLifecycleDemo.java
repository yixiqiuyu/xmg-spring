package com.yixiqiuyu.spring.ioc.bean.lifecycle;

import com.yixiqiuyu.spring.overview.domain.SuperUser;
import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * @author yixiqiuyu
 * @Description Bean 实例化生命周期示例
 * @Date 2022/6/16 10:12
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

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
    }



    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{

        /**
         *
         * @param beanClass
         * @param beanName
         * @return  @return the bean object to expose instead of a default instance of the target bean,
         * 	 * or {@code null} to proceed with default instantiation
         * @throws BeansException
         */
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            // 把配置完成的 superUser Bean 覆盖
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            // 保持 Spring IoC容器的实例化操作
            return null;
        }

    }
}
