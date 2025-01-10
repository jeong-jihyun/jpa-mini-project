package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.ReviProcDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviProcDiscInfoRepository extends JpaRepository<ReviProcDiscInfoEntity, Long> {
}
