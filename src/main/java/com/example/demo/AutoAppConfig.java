package com.example.demo;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration // 설정정보 파일이니까 @Configuration 어노테이션을 필수다.
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 컴포넌트 스캔을 이용하여 @Component 어노테이션이 붙은 클래스를 전부 스프링빈으로 등록해줄것이다.
//그전에 필터를 통해서 @Configuration 어노테이션이 붙은 클래스는 등록하지않는다 -> 기존에있는 AppConfig가 등록되면 안되니까.
//컴포넌트스캔에 걸리는 이유는 @Configuration안에는 @Component이 있기떄문이다.
public class AutoAppConfig {
    //내용이 없다.

}
