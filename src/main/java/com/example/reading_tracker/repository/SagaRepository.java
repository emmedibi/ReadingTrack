package com.example.reading_tracker.repository;

import com.example.reading_tracker.bean.Saga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaRepository extends JpaRepository<Saga, Long> {

    Saga findByName(String saga_name);
}
