package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.CbRighIssuDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CbRighIssuDiscInfoRepository extends JpaRepository<CbRighIssuDiscInfoEntity, Long> {
}
