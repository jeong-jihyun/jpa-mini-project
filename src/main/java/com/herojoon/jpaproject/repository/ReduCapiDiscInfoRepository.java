package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.ReduCapiDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReduCapiDiscInfoRepository extends JpaRepository<ReduCapiDiscInfoEntity, Long> {
}
