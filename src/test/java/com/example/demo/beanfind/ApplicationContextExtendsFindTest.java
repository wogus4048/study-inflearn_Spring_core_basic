package com.example.demo.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 , 자식이 둘 이상이 있으면 중복오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 , 자식이 둘 이상이 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class); //이름도 같이 전달
        assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class); // DiscountPolicy의 객체(구현체)가 맞는지 체크
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회") // 안좋은방법이다. 구현체로 검색하기때문
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);//찾고자 하는 타입으로 바로검색( 하나밖에 없을떄)
        assertThat(bean).isInstanceOf(DiscountPolicy.class); // 인터페이스를 가지고 검증한다. 인터페이스를 상속한 구현체클래스일것이니까.
//        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);  구현체클래스로 검증해도 당연히 성공
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        //출력해볼수도있음 실제 테스트 케이스를 짤때는 출력물을 남기면 안된다. 출력물을 눈으로 확인안하려고 테스트를 짜는것이기 때문이다.
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }

        //검증  간단하게 2개인지 체크해서 검증한다.
        assertThat(beansOfType.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 --> Object 타입으로") //스프링컨테이너안에 등록된 모든 빈이 조회된다.
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {

        //DiscountPolicy로 조회한다면 해당 인터페이스를 상속한 두 빈들이 조회될것이다.
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }

}
