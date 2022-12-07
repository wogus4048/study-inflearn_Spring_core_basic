package com.example.demo.order;

public interface OrderService {
    //주문생성 메소드 (리턴으로 주문결과를 반환)
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
