package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.GeneMeetStocPublNotiDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneMeetStocPublNotiDiscInfoRepository extends JpaRepository<GeneMeetStocPublNotiDiscInfoEntity, Long> {
}
