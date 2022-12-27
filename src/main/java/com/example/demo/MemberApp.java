package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig(); //AppConfig객체생성
//        MemberService memberService = appConfig.memberService(); //AppConfig에서 멤버서비스를 만들어준다. (AppConfig에서 설정한 멤버서비스임플구현체가 들어갈것이다)
//        //그래도 밑에 동작이 잘 작동하는지 체크

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService",
            MemberService.class);//객체의 이름은 AppConfig에 적은 메서드이름으로 적어준다. 두번쨰는 타입을 적어준다.

        Member memberA = new Member(1L, "memberA", Grade.VIP); //테스트용 멤버생성
        memberService.join(memberA); //가입메소드 사용

        Member findMember = memberService.findMember(1L); // 정상 가입됬는지 findMember 메소드를 사용해본다.
        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());
    }

}

