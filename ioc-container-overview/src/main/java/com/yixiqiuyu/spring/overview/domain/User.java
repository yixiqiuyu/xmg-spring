package com.yixiqiuyu.spring.overview.domain;

/**
 * @Description: 用户类
 * @Author: thinkpad
 * @Date: 2022/2/9  20:22
 */

public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static User createUser() {
        return new User();
    }
}
