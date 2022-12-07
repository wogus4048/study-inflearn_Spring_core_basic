package com.example.demo.member;

public interface MemberRepository {

    void save(Member member); //구현해야하는 메소드
    Member findById(Long memberId); //구현해야하는 메소드
}
