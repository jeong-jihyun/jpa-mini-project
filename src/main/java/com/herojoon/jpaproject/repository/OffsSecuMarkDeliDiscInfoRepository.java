package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.OffsSecuMarkDeliDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffsSecuMarkDeliDiscInfoRepository extends JpaRepository<OffsSecuMarkDeliDiscInfoEntity, Long> {
}
