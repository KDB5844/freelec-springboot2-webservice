package com.kdb.book.springboot.service.posts;

import com.kdb.book.springboot.domain.posts.Posts;
import com.kdb.book.springboot.domain.posts.PostsRepository;
import com.kdb.book.springboot.web.dto.PostsListResponseDto;
import com.kdb.book.springboot.web.dto.PostsResponseDto;
import com.kdb.book.springboot.web.dto.PostsSaveRequestDto;
import com.kdb.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)     //readOnly=true 옵션을 주면 트랙잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다. CUD 기능이 전혀 없는 서비스 메소드에 사용하기 좋음
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
