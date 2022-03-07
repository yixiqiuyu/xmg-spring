package com.yixiqiuyu.spring.dependency.lookup;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.jws.soap.SOAPBinding;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yixiqiuyu
 * @Description {@link NoUniqueBeanDefinitionException} 异常演示
 * @Date 2022/3/7 23:58
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 将当前类 TypeSafetyDependencyLookupDemo 作为配置类(Configuration Class)
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        applicationContext.getBean(User.class);

        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Bean.class);

        for (Map.Entry<String, Object> next : beansWithAnnotation.entrySet()) {
            System.out.println(next.getKey() + ":" + next.getValue());
        }
        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring 应用上下文存在%d个%s类型的Bean，具体原因：%s%n", e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }
        applicationContext.close();
    }

    @Bean(name = "myUser")
    public User userBeanOne() {
        return new User();
    }

    @Bean(name = "myUser")
    public User userBeanTwo() {
        return new User();
    }

    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }
}
