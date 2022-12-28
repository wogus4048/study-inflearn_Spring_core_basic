package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService {
    //MemberService의 구현체가 1개인경우 구현체 뒤에 Impl이라는 단어를 붙여 생성하는게 관례상 많이 쓰는 방법이라고 한다.

    // 멤버 리포지토리안에 있는 메소드를 사용해야하므로 선언해준다.
    //인터페이스 변수를 만들고 , 그 안에 구현체객체를 저장해준다. (다형성을 이용함)
    private final MemberRepository memberRepository;


    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); // save를 하게되면 다형성에 의해 MemoryMemberRepository에 있는 save메소드가 실행될것이다.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //싱글톤패턴이 적용됬는지 테스트용도 메소드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
