package com.example.demo.singleton;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            AppConfig.class);

        //구현체클래스로 꺼내야한다. 구현체 클래스안에다가 테스트용 메소드를 만들어놨으니까,  원래는 구체타입으로 꺼내는것은 좋지않다.
        //
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);


        /*
        MemberService memberService = ac.getBean(MemberServiceImpl.class); 이렇게 해준다면 멤버서비스Impl에 테스트용으로 만든
        getMemberRepository를 사용할수 있을거라 생각했지만 MemberService 인터페이스 변수에 저장하기때문에 구현체가 저장은 되지만
        구현체 내부에 만든 메소드는 사용할 수 없었다
        MemberServiceImpl memberService = ac.getBean(MemberService.class); 이것또한 컴파일 오류를 발생하였다.
        멤버서비스를 상속한 멤버서비스임플이 나오니까 저장될줄 알았는데 되지않았다.
        부모는 자식을 품을수있지만, 자식은 부모를 품을수없다. 부모는 자식을 저장할수있지만 , 자식은 부모를 저장할 수 없는 상속개념인것같다.
        멤버서비스 인터페이스 변수가 다른 구현체를 저장할 순 있지만 , 멤버서비스의 구현체변수가 다른 멤버서비스구현체를 저장할 수도있는 코드이기때문이다.

        MemberService memberService = ac.getBean(MemberService.class);로 바꾸고 저장된 멤버서비스를 출력해보았더니 멤버시버스임플이였다.
        */

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberRepository1)
            .isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class); //구성설정파일도 스프링빈으로 등록되니 꺼내볼수있다.

        System.out.println("bean = " + bean.getClass());
    }

}
