package com.example.demo.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyexcludeComponent { //임의로 만든 어노테이션,이 어노테이션이 붙은 애들은 스캔에 제외한다.

}
