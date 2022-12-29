package com.example.demo.autowired;

import com.example.demo.member.Member;
import jakarta.annotation.Nullable;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            TestBean.class); //TestBean.class안에 @Bean이 없으니 아무것도 빈등록이 안될것이다.

    }

    static class TestBean {

        @Autowired(required = false) // required = false 를 해뒀을때, 자동 주입할 대상이 없으면 (== member가 빈에 등록안되어있으면) 수정자 메서드 자체가 호출 안된다.
        public void setNoBean1(Member member) { // Member는 스프링빈이 등록되어있지않다. member는 그냥 엔티티클래스
            System.out.println("member1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) { //@Nullable 어노테이션을 붙이면 자동 주입할 대상이 없을시 null값이 입력된다.
            System.out.println("member2 = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("member3 = " + member);
        }
    }

}


