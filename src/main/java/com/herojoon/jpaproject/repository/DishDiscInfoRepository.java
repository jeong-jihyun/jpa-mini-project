package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.DishDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishDiscInfoRepository extends JpaRepository<DishDiscInfoEntity, Long> {
}
