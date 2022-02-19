package com.yixiqiuyu.spring.overview.domain;

import com.yixiqiuyu.spring.overview.annotation.Super;

/**
 * @Description: TODO
 * @Author: thinkpad
 * @Date: 2022/2/9  20:55
 */

@Super
public class SuperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
