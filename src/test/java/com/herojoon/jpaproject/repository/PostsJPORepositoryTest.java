package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.PostsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostsJPORepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void insert(){
        postsRepository.save(PostsEntity.builder().
                title("테스트 게시글1").
                content("테스트 본문1").
                author("jeongjih@live.com").
                build());

    }
}