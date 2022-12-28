package com.example.demo.singleton;

public class StatefulService { //주문이라는 메소드를 이용해서 가격을 저장하려고 만든 클래스

    private int price;// 상태를 유지하는 필드 ,private를 이용해서 지정한 메소드로만 값을 설정,불러오기 하려고했다.

    public void order(String name, int price) {
        System.out.println("name = " + name + "price =" + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }

}
