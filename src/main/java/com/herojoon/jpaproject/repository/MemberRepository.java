package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.MemberJPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberJPO, Long> {
}
