package com.kdb.book.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter                         //선언된 모든 필드의 get메소드를 생성해 줍니다.
@RequiredArgsConstructor        //final이 없는 필드는 생성자에 포함되지 않습니다.
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
