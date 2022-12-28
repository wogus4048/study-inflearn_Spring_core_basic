package com.example.demo.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class); // 클래스명이 빈 이름인데 , 첫글자는 소문자가 된다. 그래서 빈 이름은 beanA
        assertThat(beanA).isNotNull(); // beanA는 스캔대상이므로 null이면 안된다.

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class)); //beanB를 가져오려고하면 예외가 발생한다.

    }


    @Configuration
    @ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), //스캔에 포함 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyexcludeComponent.class) // 스캔 제외 설정
    )
    static class ComponentFilterAppConfig { //테스트용 구성설정 클래스이다, 구성설정 클래스이므로 @Configuration붙여줘야하고
        //스캔을 테스트할거니까 @ComponentScan 붙여준다.
    }
}
