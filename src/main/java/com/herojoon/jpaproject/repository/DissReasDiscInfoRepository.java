package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.DissReasDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DissReasDiscInfoRepository extends JpaRepository<DissReasDiscInfoEntity, Long> {
}
