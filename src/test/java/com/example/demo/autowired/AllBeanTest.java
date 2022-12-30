package com.example.demo.autowired;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.AppConfig;
import com.example.demo.AutoAppConfig;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
            DiscountService.class); //전달인자로 넣은것은 빈으로 등록된다.(DiscountService는 스프링컨테이너 생성할때 전달인자로 넣어서 직접 빈 등록해준거다.)
        // 전달인자 클래스중 그안에 @Bean 또는 @ComponentScan이 있다면 그것도 진행해서 빈을 추가 등록해준다. 물론 구성설정 클래스는 @Configuration이 필수다.

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(userA, 10000, "fixDiscountPolicy");

        assertThat(discountPrice).isEqualTo(1000);
    }

    static class DiscountService{ // 기존 오더서비스를 손대면 복잡해지니 테스트용으로 하나 만든다.

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        //@RequiredArgsConstructor로 대체가능 , 이 테스트에는 출력할거라 직접 생성.
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap,
            List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            //의존관계 주입 잘 됬는지 출력
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        int discount(Member member, int price, String discountCode) {
            //할인코드를 빈 이름이랑 매칭시킨다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode); //가져온 빈값들중 해당 빈이름의 스프링빈을 가져온다.
            return discountPolicy.discount(member, price); //가져온 DiscountPolicy의 메소드를 이용해서 할인 금액을 리턴해준다.
        }
    }

}

