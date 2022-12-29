package com.example.demo.discount;

import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary
@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) { //할인금액이 얼마인지 리턴해준다.
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100; // 지금은 10%이니까 price의 10이 얼마인지 -> 얼마할인되는지 리턴
        } else {
            return 0;
        }

    }
}
