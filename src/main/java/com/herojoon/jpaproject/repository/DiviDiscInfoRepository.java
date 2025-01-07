package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.DiviDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiviDiscInfoRepository extends JpaRepository<DiviDiscInfoEntity, Long> {

}
