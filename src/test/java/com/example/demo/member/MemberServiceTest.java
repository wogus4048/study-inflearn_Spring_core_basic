package com.example.demo.member;

import com.example.demo.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test//필수 어노테이션
    void join() {
        //given  -> 어떤값이 주어졌을때 (값 생성)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when  -> ~ 를 했을때  (테스트할 코드)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then -> 이렇게 된다. (값 검증)
        //import org.assertj.core.api.Assertions;에 있는 Assertions를 사용해야한다.
        Assertions.assertThat(member).isEqualTo(findMember); //멤버는 찾은 멤버랑 같은지 체크

    }

}
