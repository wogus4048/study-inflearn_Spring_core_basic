package com.example.demo.member;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl(); //멤버서비스 인터페이스 변수를 이용해서 멤버서비스 구현체를 저장
        Member memberA = new Member(1L, "memberA", Grade.VIP); //테스트용 멤버생성
        memberService.join(memberA); //가입메소드 사용

        Member findMember = memberService.findMember(1L); // 정상 가입됬는지 findMember 메소드를 사용해본다.
        System.out.println("new member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());
    }

}
