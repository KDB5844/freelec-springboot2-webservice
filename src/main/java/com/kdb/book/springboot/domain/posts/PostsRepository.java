package com.kdb.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해 이런 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용한다.
 * 대표적인 예로 querydsl(추천), jooq, MyBatis등이 있다.
 * querydsl 이 좋은 이유
 * 1. 타입 안정성이 보장 됨 - 단순한 문자열로 쿼리를 생성하는 것이 아니라, 메소드를 기반으로 쿼리를 생성하기 때문에 오타나 존재하지 않는 칼럼명을 명시할 경우 IDE에서 자동 검출
 * 2. 국내 많은 회사에서 사용 중
 * 3. 레퍼런스가 많음
 */
public interface PostsRepository  extends JpaRepository<Posts, Long> {          //Entity 클래스, PK 타입, Entity 클래스와 함께 위치 해야함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")  //SpringDataJpa 에서 제공하지 않는 메소드는 이처럼 쿼리로 작성 가능
    List<Posts> findAllDesc();

}
