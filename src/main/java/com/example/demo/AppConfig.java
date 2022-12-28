package com.example.demo;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig { //프로젝트의 객체 생성,구성,주입 환경설정을 하는 중요 역할

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(MemberRepository());
    }
    @Bean
    public MemberRepository MemberRepository() {
        System.out.println("AppConfig.MemberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(MemberRepository(), DiscountPolicy());
    }
    @Bean
    public DiscountPolicy DiscountPolicy() {
        return new RateDiscountPolicy();
    }

}
