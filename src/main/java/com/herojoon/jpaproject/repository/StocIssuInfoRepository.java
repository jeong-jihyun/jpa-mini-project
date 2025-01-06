package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.StocIssuInfoJPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocIssuInfoRepository extends JpaRepository<StocIssuInfoJPO, Long> {
}
