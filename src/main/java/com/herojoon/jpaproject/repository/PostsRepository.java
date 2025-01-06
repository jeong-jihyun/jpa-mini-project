package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.PostsJPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<PostsJPO, Long> {
}