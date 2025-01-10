package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.OffsSecuMarkListDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffsSecuMarkListDiscInfoRepository extends JpaRepository<OffsSecuMarkListDiscInfoEntity, Long> {
}
