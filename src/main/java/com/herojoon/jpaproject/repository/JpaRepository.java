package com.herojoon.jpaproject.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

@NoRepositoryBean
public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    @Override
    List<T> findAll();

    // ID 값을 리스트 형식으로 받아 레코드 조회
    @Override
    List<T> findAllById(Iterable<ID> ids);

    // 엔티티를 리스트 형식으로 받아 테이블에 한번에 저장
    @Override
    <S extends T> List<S> saveAll(Iterable<S> entities);

    // 현재 JPA Context 가지고 있는 값을 DB에 반영
    void flush();

    // 엔티티를 리스트 형식으로 받아 레코드를 한번에 삭제
    @Deprecated
    default void deleteInBatch(Iterable<T> entities) { deleteAllInBatch(entities); }

    // 조건없이 테이블의 전체 레코드 삭제
    void deleteAllInBatch(Iterable<T> entities);

    // ID 값으로 해당 엔티티 하나 조회
    @Deprecated
    T getOne(ID id);
}
