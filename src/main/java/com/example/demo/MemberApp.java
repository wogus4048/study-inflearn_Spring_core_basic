package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig(); //AppConfig객체생성
        MemberService memberService = appConfig.memberService(); //AppConfig에서 멤버서비스를 만들어준다. (AppConfig에서 설정한 멤버서비스임플구현체가 들어갈것이다)
        //그래도 밑에 동작이 잘 작동하는지 체크

        Member memberA = new Member(1L, "memberA", Grade.VIP); //테스트용 멤버생성
        memberService.join(memberA); //가입메소드 사용

        Member findMember = memberService.findMember(1L); // 정상 가입됬는지 findMember 메소드를 사용해본다.
        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());
    }

}
