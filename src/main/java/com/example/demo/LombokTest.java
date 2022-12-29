package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter //게터
@Setter // 세터
@AllArgsConstructor // 전체 필드값(객체 변수 모두)을 전달받는 생성자 함수 생성
@ToString //클래스명만 입력해도 toString 붙인거처럼 출력한다.
@RequiredArgsConstructor // final이 붙거나, @Nonnull이 붙은 필수값이 붙은 필드를 가진 변수들을 받는 생성자를 만들어준다.

public class LombokTest {
    private String name;
    private int age;
    private final int height;
    private final int weight;

    public static void main(String[] args) {
        LombokTest lombokTest = new LombokTest("asd",12,180,100); //롬복테스트안에는 따로 생성자가 없는데, @AllArgsConstructor덕분에 필드값을 다 전달받는 생성자 생김
        LombokTest lombokTest2 = new LombokTest(180, 100); // @RequiredArgsConstructor 덕분에 필수값 2개를 받는 생성자가 만들어진다.
        lombokTest.setName("수정후이름");
        System.out.println("lombokTest name = " + lombokTest.name);
        System.out.println(lombokTest); // lombokTest.toString() 할 필요없이 클래스명만 입력해도 toString 붙인거처럼 출력한다.
    }
}
