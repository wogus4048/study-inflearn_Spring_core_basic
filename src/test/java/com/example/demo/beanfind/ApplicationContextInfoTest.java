package com.example.demo.beanfind;

import com.example.demo.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest { // junit5부터는 public이 없어도 된다.

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //컨테이너에 등록된 빈 이름들 배열을 가져온다. 
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);// 받아온 빈의 이름을 가지고 하나씩 꺼내본다, 타입은 지정안했기때문에 object로 꺼내진다.
            System.out.println("name = " + beanDefinitionName + "    Object : "+bean);

        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //컨테이너에 등록된 빈 이름들 배열을 가져온다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);// 빈에 대한 메타데이터정보를 가져오는 메소드

            //스프링이 필요해서 알아서 등록한 빈들이 아니라 내가 어플리케이션은 개발하기위해 등록한 빈들은 ROLE_APPLICATION이라는 role을 가진다.
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);// 그런 빈들만 가져와서 출력해본다.
                System.out.println("name = " + beanDefinitionName + "    Object : "+bean);
            }
        }
    }
}
