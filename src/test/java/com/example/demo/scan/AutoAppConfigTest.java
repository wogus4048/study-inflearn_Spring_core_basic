package com.example.demo.scan;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.AutoAppConfig;
import com.example.demo.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            AutoAppConfig.class);

        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //빈 메타정보들 이름을 가져온다. = 빈 이름을 가져온다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); //빈 메타정보 이름을 이용해서 해당 빈 메타정보를 가져온다.
            // 빈 이름을 이용해서 빈 메타정보를 가져온다. getBeanDefinition메소드를 쓰기위해 AnnotationConfigApplicationContext객체에 담아 사용한다.

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { //빈 메타정보안에 Role정보를 이용해서, 내가 등록한 빈이라면 (스프링 자체에서 올린게아니고)
                System.out.println("beanDefinitionName = " + beanDefinitionName); //빈 이름을 출력해본다.
            }
        }

        MemberService memberService = ac.getBean(MemberService.class); //검증을 위해 스프링빈에 등록된 멤버서비스 구현체를 꺼내온다.
        assertThat(memberService).isInstanceOf(MemberService.class); //멤버서비스의 구현체인지 검증

    }

}
