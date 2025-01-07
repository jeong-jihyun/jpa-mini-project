package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.CapiIncrWithConsBonuIssuDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 유상증자결정공시정보조회 Repository
 */
@Repository
public interface CapiIncrWithConsBonuIssuDiscInfoRepository extends JpaRepository<CapiIncrWithConsBonuIssuDiscInfoEntity, Long> {
}
