package com.yixiqiuyu.spring.ioc.bean.scope.web;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author yixiqiuyu
 * @Description
 * @Date 2022/5/23 23:47
 */

@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    //@RequestScope
    //@SessionScope
    @ApplicationScope
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("zhangsan");
        return user;
    }
}
