package com.yixiqiuyu.dependency.injection;

import com.yixiqiuyu.spring.overview.domain.User;

/**
 * @author yixiqiuyu
 * @Description {@link com.yixiqiuyu.spring.overview.domain.User} 的 Holder 类
 * @Date 2022/3/9 20:54
 */
public class UserHolder {

    private User user;

    public UserHolder() {

    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
