package com.example.demo.beandefinition;

import com.example.demo.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
        //BeanDefinition 확인
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 빈 메타정보의 이름들을 가져온다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); //빈 메타정보의 이름을 이용해서 빈 메타정보를 가져온다.

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { //필요해서 스프링빈에 추가한 빈 메타정보라면
                System.out.println( //빈 메타정보 이름과 빈 메타정보 자체를 출력해본다.
                    "beanDefinitionName = " + beanDefinitionName + "  beanDefinition = "
                        + beanDefinition);
            }
        }
    }
}
