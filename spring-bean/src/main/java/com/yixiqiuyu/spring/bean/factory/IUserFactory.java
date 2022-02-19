package com.yixiqiuyu.spring.bean.factory;

import com.yixiqiuyu.spring.overview.domain.User;

/**
 * {@link User} 工厂类
 *
 * @Author: yixiqiuyu
 * @Date: 2022/2/19  21:13
 */

public interface IUserFactory {
    /**
     * 创建user的工厂方法 JDK1.8的默认接口实现
     *
     * @return
     */
    /*default User createUser() {
        return User.createUser();
    }*/

    User createUser();
}
