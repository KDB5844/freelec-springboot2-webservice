package com.kdb.book.springboot.service.posts;

import com.kdb.book.springboot.domain.posts.Posts;
import com.kdb.book.springboot.domain.posts.PostsRepository;
import com.kdb.book.springboot.web.dto.PostsResponseDto;
import com.kdb.book.springboot.web.dto.PostsSaveRequestDto;
import com.kdb.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
        /**
         * update 기능에서 쿼리를 날리는 부분이 없다.
         * 그게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
         * 영속성 컨텍스는 엔티티를 영구 저장하는 환경
         * 엔티티가 영속성 컨텍스트에 포함되어 있냐/아니냐로 갈린다.
         * 트랜젝션 안에서 데이터베이스에서 데이터를 가져오면 영속성 컨텍스트가 유지된 상태이다.
         * 트랜젝션이 끝나는 시점에 해당 테이블에 변경분을 반영한다. 이 개념을 더티 채킹(dirty checking)이라고 한다.
         */
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.getOne(id);
        PostsResponseDto responseDto = new PostsResponseDto(posts);
        return responseDto;
    }

}
