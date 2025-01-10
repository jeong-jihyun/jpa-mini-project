package com.herojoon.jpaproject.repository;

import com.herojoon.jpaproject.entity.ProcByCredBankDiscInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcByCredBankDiscInfoRepository extends JpaRepository<ProcByCredBankDiscInfoEntity, Long> {
}
