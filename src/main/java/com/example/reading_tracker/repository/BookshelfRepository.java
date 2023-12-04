package com.example.reading_tracker.repository;

import com.example.reading_tracker.bean.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {

    List<Bookshelf> findAllByUserId(Long userId);
    Bookshelf findByName(String bookshelf_name);
}
