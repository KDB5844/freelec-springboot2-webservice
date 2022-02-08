package com.kdb.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)          //이 어노테이션이 생성될 수 있는 위치를 지정, PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음
@Retention(RetentionPolicy.RUNTIME)     //언제까지 남아 있을지를 결정, RUNTIME 으로 지정했으니 런타임(메모리에 올라와있을 때도) 남아있어야 하는것들
public @interface LoginUser {   //해당 파일을 어노테이션 클래스로 지정
}
