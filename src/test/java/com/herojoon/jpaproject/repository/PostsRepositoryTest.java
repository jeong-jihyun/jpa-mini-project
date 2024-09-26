package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void insert(){
        postsRepository.save(Posts.builder().
                title("테스트 게시글1").
                content("테스트 본문1").
                author("jeongjih@live.com").
                build());

    }
}