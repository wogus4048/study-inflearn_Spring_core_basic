package com.example.demo.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean { //InitializingBean 구현 -> 초기화 콜백관련 DisposableBean 소멸 콜백관련
    private String url;


    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }


    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    //연결된 곳에 메시지 보내기
    public void call(String message) {
        System.out.println("call" + url + " message : " + message);
    }
    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    @Override //오버라이드
    public void afterPropertiesSet() throws Exception { //의존관계 주입이 끝나면 실행
        System.out.println("afterPropertiesSet 실행");
        connect(); //초기화가 다되고나서 연결을 시도한다.
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception { //소멸전 콜백 (빈이 소멸할때 실행)
        System.out.println("destory 실행");
        disconnect(); //빈 소멸전 연결을 끊어준다.
    }
}
