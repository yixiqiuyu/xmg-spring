package com.yixiqiuyu.spring.bean.factory;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description: {@link com.yixiqiuyu.spring.overview.domain.User}Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 * @Author: yixiqiuyu
 * @Date: 2022/2/19  22:47
 */

public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
