package com.example.demo.discount;

import static org.assertj.core.api.Assertions.*;


import com.example.demo.member.Grade;
import com.example.demo.member.Member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();


    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닌경우 할인이 적용되지않아야한다.")
    void vip_x() {
        //given
        Member member = new Member(1L, "memberBasic", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then

        assertThat(discount).isEqualTo(0);

    }
}