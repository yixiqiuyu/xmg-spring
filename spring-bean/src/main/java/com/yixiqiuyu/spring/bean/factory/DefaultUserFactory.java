package com.yixiqiuyu.spring.bean.factory;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Description: IUserFactory的实现类
 * @Author: yixiqiuyu
 * @Date: 2022/2/19  21:17
 */

public class DefaultUserFactory implements IUserFactory, InitializingBean {
    // Bean初始化的三种方式 @PostConstruct注解， @Bean(initMethod = "initUserFactory")和实现InitializingBean接口
    // 三种方式的优先级为 @PostConstruct > @Bean(initMethod = "") > InitializingBean接口
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中....");
    }


    public void initUserFactory() {
        System.out.println("自定义初始化方法 initUserFactory(): UserFactory 初始化中...");
    }

    @Override
    public User createUser() {
        return User.createUser();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() : UserFactory 初始化中...");
    }
}
