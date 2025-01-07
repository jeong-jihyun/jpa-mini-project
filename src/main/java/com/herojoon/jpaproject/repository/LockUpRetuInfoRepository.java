package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.LockUpRetuInfoJPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockUpRetuInfoRepository extends JpaRepository<LockUpRetuInfoJPO, Long> {
}
