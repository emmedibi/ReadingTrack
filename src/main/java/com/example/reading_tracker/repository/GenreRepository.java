package com.example.reading_tracker.repository;

import com.example.reading_tracker.bean.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String genre_name);
}
