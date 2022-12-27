package com.example.demo.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); // 자기자신을 내부에 private static으로 가지고있는다.
    // static으로 선언했으므로 클래스레벨에 올라가 단 하나만 존재하게 된다.


    //객체를 하나만 가지기 위해서 자기자신안에다가 자기자신 객체를 만들었다.
    public static SingletonService getInstance() { //만든 단 하나뿐인 객체를 공유해서 쓰려면 가져오는 메소드가 필요하므로 getInstance()를 만든다.
        return instance; //만든걸 리턴해준다.
    }

    //내가 생성한 객체만 가져다 쓰게끔하고 싶은데 , 외부에서 클래스의 생성자를 통해서 객체를 새로 만드려고하는것을 방지하기위해 private를 붙인 생성자를 정의한다.
    private SingletonService() {

    }

    public void logic(){ // 메소드 하나 넣어줬음
        System.out.println("싱글톤 객체 로직 호출");
    }

}

