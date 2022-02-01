package com.kdb.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository  extends JpaRepository<Posts, Long> {          //Entity 클래스, PK 타입, Entity 클래스와 함께 위치 해야함
}
