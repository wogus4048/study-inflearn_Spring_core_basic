package com.example.demo.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.AppConfig;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AppConfig.class); //스프링 컨테이너 생성

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService",
            MemberService.class); // 이름과 타입으로 빈을 조회하고 가져온다.
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(
            MemberServiceImpl.class);// 받아온 멤버서비스가 멤버서비스임플의 객체냐? 라고 물어보는 메소드
        //Assertions는 알트엔터로 static화 한다.

        ac.getBean(MemberRepository.class);
        ac.getBean(DiscountPolicy.class);
    }

    @Test
    @DisplayName("이름 없이 빈 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); // 이름빼고 타입으로만 조회해서 가져와본다.
        assertThat(memberService).isInstanceOf(
            MemberService.class);// 받아온 멤버서비스가 멤버서비스임플의 객체냐? 라고 물어보는 메소드
    }

    @Test
    @DisplayName("구체 타입으로로 조회")
        // 인터페이스가 아닌 구현체 클래스로 검색도 가능하다. 스프링컨테이너에 등록된 객체이므로
    void findBeanByName2() { //하지만 이렇게 적는게 좋지않다. -> 구현체를 바꾸면 이 코드 또한 수정이 필요할것이니까, 그러나 가끔 필요할때가 있다.
        MemberServiceImpl memberService = ac.getBean("memberService",
            MemberServiceImpl.class); //구현체로 검색한다.
        assertThat(memberService).isInstanceOf(
            MemberServiceImpl.class);// 받아온 멤버서비스가 멤버서비스임플의 객체냐? 라고 물어보는 메소드

    }

    @Test // 조회가 안될때 -> 항상 테스트는 실패 테스트를 만들어줘야한다.
    @DisplayName("빈 이름으로 조회가 안될때")
    void findBeanByNameX() {
        //예외가 터져야 성공이니까 왼쪽에는 해당 예외를 적어주고, 오른쪽에는 어떤 로직을 실행했을때? 를 적는다.(람다식으로)
        assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("xxx", MemberService.class));
    }
}
