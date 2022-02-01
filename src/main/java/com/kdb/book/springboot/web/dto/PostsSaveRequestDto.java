package com.kdb.book.springboot.web.dto;

import com.kdb.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    /**
     * Entity 클래스와 거의 유사한 형태지만 Dto 클래스를 추가로 생성함.
     * Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
     * Entity 클래스를 데이터베이스와 맞닿는 핵심 클래스 이다.
     * Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경된다.
     * 화면 변경은 사소한 기능 변경이지만 이를 위해 연결된 Entity 클래스를 변경하는 것은 너무 큰 변경이다.
     * Request와 Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요하다
     * 그로 인해 View Layer 와 DB Layer의 역할 분리를 철자하게 하는 것이 좋은 설계
     */

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
