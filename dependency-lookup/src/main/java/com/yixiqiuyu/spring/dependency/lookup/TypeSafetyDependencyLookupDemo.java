package com.yixiqiuyu.spring.dependency.lookup;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yixiqiuyu
 * @Description 类型安全 依赖查找示例
 * @Date 2022/3/5 21:01
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将当前类 TypeSafetyDependencyLookupDemo 作为配置类(Configuration Class)
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        // 演示 BeanFactory#getBean方法的安全性（不安全，会报错）
        displayBeanFactoryGetBean(applicationContext);

        // 演示ObjectFactory#getObject方法的安全性（不安全，会报错）
        displayObjectFactoryGetObject(applicationContext);

        // 演示 ObjectProvider#getIfAvailable方法的安全性（安全）
        displayObjectProviderIfAvailable(applicationContext);

        //
        displayListableBeanFactory(applicationContext);

        //
        displayObjectProviderStream(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        beanProvider.stream().forEach(System.out::println);
    }

    private static void displayListableBeanFactory(AnnotationConfigApplicationContext applicationContext) {
        DefaultListableBeanFactory defaultListableBeanFactory = applicationContext.getDefaultListableBeanFactory();
        printBeansException("displayListableBeanFactory", () -> defaultListableBeanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> userObjectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", () -> userObjectFactory.getObject());
    }


    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        /*try {
            beanFactory.getBean(User.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }*/
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("source from " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
