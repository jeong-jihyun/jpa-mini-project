package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.Member;
import com.herojoon.jpaproject.entity.StocIssuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocIssuInfoRepository extends JpaRepository<StocIssuInfo, Long> {
}
