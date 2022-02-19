package com.yixiqiuyu.spring.bean.factory;

import com.yixiqiuyu.spring.overview.domain.User;

/**
 * @Description: IUserFactory的实现类
 * @Author: yixiqiuyu
 * @Date: 2022/2/19  21:17
 */

public class DefaultUserFactory implements IUserFactory{
    @Override
    public User createUser() {
        return User.createUser();
    }
}
