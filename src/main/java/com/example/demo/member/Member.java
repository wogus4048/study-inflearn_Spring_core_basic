package com.example.demo.member;

import lombok.*;

@Getter // 게터
@Setter // 세터
@AllArgsConstructor // 모든변수를 매개변수로 가지는 생성자 생성
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    //Generate를 이용해서 직접 ,Constructor, getter,setter 만들어줘도 된다.
}
