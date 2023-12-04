package com.example.reading_tracker.service;

import com.example.reading_tracker.bean.Genre;
import com.example.reading_tracker.bean.Tag;
import com.example.reading_tracker.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public boolean checkGenreName(String genre_name){
        if((genreRepository.findByName(genre_name) != null) || (genreRepository.findByName(genre_name.toUpperCase()) != null)){
            return true;
        }
        return false;
    }

    public Genre addAGenre(String genre_name){
        Genre genre = new Genre();
        genre.setGenre_name(genre_name);
        return genreRepository.save(genre);
    }

    public Genre findGenre(String genre_name){
        return genreRepository.findByName(genre_name);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
