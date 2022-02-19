package com.yixiqiuyu.spring.bean.beandefinition;

import com.yixiqiuyu.spring.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Description: BeanDefinition 构建示例
 * @Author: thinkpad
 * @Date: 2022/2/14  21:51
 */

public class BeanDefinitionDemo {
    public static void main(String[] args) {
        // 1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("id", 20)
                .addPropertyValue("name", "yixiqiuyu");
        // 获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // BeanDefinition并非Bean的终态，可以自定义修改
        //beanDefinition.setLazyInit(true);

        // 2.通过AbstractBeanDefinition 及其派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过 MutablePropertyValues 批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("id", 1).add("name", "yixiqiuyu");
        //      mutablePropertyValues.addPropertyValue("id",10);
//        mutablePropertyValues.add("name","yixiqiuyu");
        // 通过 setPropertyValues 批量操作属性
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        genericBeanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
    }
}
