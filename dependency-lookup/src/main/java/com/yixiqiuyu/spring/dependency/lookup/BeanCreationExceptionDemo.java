package com.yixiqiuyu.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author yixiqiuyu
 * @Description {@link BeanCreationException} 示例
 * @Date 2022/3/8 22:56
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册BeanDefinition Bean Class是一个POJO普通类
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动应用上下文
        applicationContext.refresh();


        applicationContext.close();
    }

    static class POJO implements InitializingBean {
        // @PostConstruct会先于InitializingBean执行
        @PostConstruct
        void init() throws Throwable {
            throw new Throwable("init(): For purposes......");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet(): For purposes......");
        }
    }
}
