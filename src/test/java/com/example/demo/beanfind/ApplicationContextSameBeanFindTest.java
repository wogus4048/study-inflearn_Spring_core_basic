package com.example.demo.beanfind;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SameBeanConfig.class); //스프링 컨테이너 생성할건데 기존에 있던 AppConfig.java를 손대서 중복빈을 생성하는것보다 아래서 간단하게 config를 생성해서 넣는다.

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
            () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있다면, 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1",
            MemberRepository.class);// 간단하게 이름을 지정해주면 된다.
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        //iter 치고 tab 눌러서 아래 반복문 쉽게 생성하기.
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value = " + beansOfType.get(key));
        } // key는 빈 이름 , value에는 타입이 나온다.
        System.out.println("beansOfType = " + beansOfType); // map 받은거 그냥 한번 출력도 해본다.

        //검증 , 디테일하게 해야하지만 간단하게한다 (2개맞는지 체크)
        assertThat(beansOfType.size()).isEqualTo(2);

    }



    @Configuration // 중복 빈을 만들기 위한 임시 config 클래스, 클래스안에 클래스를 생성한다는것은 겉에 클래스안에서만 이 클래스를 사용하겠다는 말이다.
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() { //빈이름은 다르지만 타입이 동일한 빈이 2개있다.
            return new MemoryMemberRepository();
        }

        // return new MemoryMemberRepository("1000");와 같이 다른 전달인자(파라미터)를 받아 새로운 성능을 가지는 리포지토리를 만들고싶을때
        // 이런 중복 타입의 빈을 가지는것은 흔하다고 한다.
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }
}
