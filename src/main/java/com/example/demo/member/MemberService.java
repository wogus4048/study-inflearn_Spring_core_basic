package com.example.demo.member;

public interface MemberService {

    void join(Member member); //구현해야하는 메소드 -> 회원가입 메소드

    Member findMember(Long memberId);//구현해야하는 메소드 -> 멤버아이디로 멤버찾기 메소드
}
