package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.BwRighIssuDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BwRighIssuDiscInfoRepository extends JpaRepository<BwRighIssuDiscInfoEntity, Long> {
}
