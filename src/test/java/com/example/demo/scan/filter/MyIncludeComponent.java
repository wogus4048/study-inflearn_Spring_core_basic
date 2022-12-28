package com.example.demo.scan.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//위의 3가지 어노테이션은 @Component 내부의 어노테이션을 복사붙여넣었다.
public @interface MyIncludeComponent { //임의로 만든 어노테이션,이 어노테이션이 붙은 애들만 컴포넌트스캔할것이다.

}
