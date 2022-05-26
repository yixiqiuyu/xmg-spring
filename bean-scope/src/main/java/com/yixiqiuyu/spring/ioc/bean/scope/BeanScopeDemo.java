package com.yixiqiuyu.spring.ioc.bean.scope;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.Set;

/**
 * @author yixiqiuyu
 * @Description Bean 的作用域示例
 * @Date 2022/5/22 11:17
 */
public class BeanScopeDemo implements DisposableBean {

    @Bean
    //默认scope是 @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static User singletonUser(){
        return createUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser(){
        return createUser();
    }

    private static User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;


    @Autowired
    private Map<String, User> users;

    // resolvable dependency
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称:%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        // 启动spring应用上下文
        applicationContext.refresh();

        // 结论1
        // Singleton Bean 无论是依赖注入还是依赖查找 均为同一个对象
        // Prototype Bean 无论是依赖注入还是依赖查找 均为新生成的对象

        // 结论2
        // 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方的依赖注入 Prototype Bean

        // 结论3
        // 无论是Singleton Bean 还是 Prototype Bean 均会执行初始化方法回调
        // 不过仅 Singleton Bean 会执行销毁方法回调

        scopedBeansByLookup(applicationContext);

        scopedBeansByInjection(applicationContext);


        // 显示地关闭Spring 应用上下文
        applicationContext.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo beanScopeDemo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("beanScopeDemo.singletonUser:" + beanScopeDemo.singletonUser);
        System.out.println("beanScopeDemo.singletonUser1:" + beanScopeDemo.singletonUser1);

        System.out.println("beanScopeDemo.prototypeUser:" + beanScopeDemo.prototypeUser);
        System.out.println("beanScopeDemo.prototypeUser1:" + beanScopeDemo.prototypeUser1);
        System.out.println("beanScopeDemo.prototypeUser2:" + beanScopeDemo.prototypeUser2);

        System.out.println("beanScopeDemo.users:" + beanScopeDemo.users);

    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println(singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println(prototypeUser);
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("当前BeanScopeDemo Bean 正在销毁中");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();

        for (Map.Entry<String, User> entry : users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            // 如果当前Bean 是 prototype scope
            if (beanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }
    }
}
