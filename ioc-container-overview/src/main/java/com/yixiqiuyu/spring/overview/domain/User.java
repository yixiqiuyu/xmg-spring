package com.yixiqiuyu.spring.overview.domain;

import com.yixiqiuyu.spring.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 用户类
 * @Author: thinkpad
 * @Date: 2022/2/9  20:22
 */


public class User implements BeanNameAware {
    private Long id;
    private String name;
    private City city;

    /**
     * 当前bean的名称
     */
    private transient String beanName;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

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
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static User createUser() {
        return new User();
    }


    @PostConstruct
    public void init() {
        System.out.println("User Bean[" + beanName +"]初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean[" + beanName +"]销毁中");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
