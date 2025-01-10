package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.OutsDireHumaResoAffaRepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutsDireHumaResoAffaRepoRepository extends JpaRepository<OutsDireHumaResoAffaRepoEntity, Long> {
}
