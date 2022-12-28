package com.example.demo.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            TestConfig.class); //아래에서 임시로 만든 구성파일을 이용해서 스프링컨테이너를 생성해준다.

        //스프링컨테이너는 싱글톤을 적용했기에 , 2번뽑아도 같은 객체가 담길것이다.
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문   서비스1를 이용해서
        statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문    서비스2를 이용해서 ( 싱글톤이라 어차피 같은 객체이다)
        statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문금액 조회  -> 예상값은 A사용자가 주문한 만원이 나와야하는데 싱글톤이 적용되어있기때문에 2만원으로 수정된 가격이 반환될것이다.
        int price = statefulService1.getPrice(); // 싱글톤이라 같은 객체를 반환받아서 하나뿐인 price변수를 변경하였으니.
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000); //틀린금액으로 반환되는지 검증
    }


    static class TestConfig{ //빈 메타정보는 StatefulService 메타정보가 생성된다. , statefulService가 빈이름, StatefulService가 빈 타입
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}