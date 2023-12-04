package com.example.reading_tracker.repository;

import com.example.reading_tracker.bean.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByName(String publisher_name);
}
