package com.example.consumer.repository;

import com.example.consumer.model.entity.ErrorRetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRetryRepository extends JpaRepository<ErrorRetryEntity, Long>,
        JpaSpecificationExecutor<ErrorRetryEntity> {
}
