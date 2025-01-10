package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.BusiSuspDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusiSuspDiscInfoRepository extends JpaRepository<BusiSuspDiscInfoEntity, Long> {
}
