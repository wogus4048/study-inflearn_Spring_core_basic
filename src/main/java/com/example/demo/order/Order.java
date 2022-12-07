package com.example.demo.order;

public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int disConntPrice;

    public Order(Long memberId, String itemName, int itemPrice, int disConntPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.disConntPrice = disConntPrice;
    }

    //최종계산 금액 리턴해주는 함수 생성
    public int calculatePrice() {
        return itemPrice - disConntPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDisConntPrice() {
        return disConntPrice;
    }

    public void setDisConntPrice(int disConntPrice) {
        this.disConntPrice = disConntPrice;
    }

    //출력을 쉽게 확인하려고 toString()까지 추가
    @Override
    public String toString() {
        return "Order{" +
            "memberId=" + memberId +
            ", itemName='" + itemName + '\'' +
            ", itemPrice=" + itemPrice +
            ", disConntPrice=" + disConntPrice +
            '}';
    }
}
