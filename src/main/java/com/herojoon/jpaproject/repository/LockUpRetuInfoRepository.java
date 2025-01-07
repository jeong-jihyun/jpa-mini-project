package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.LockUpRetuInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockUpRetuInfoRepository extends JpaRepository<LockUpRetuInfoEntity, Long> {
}
