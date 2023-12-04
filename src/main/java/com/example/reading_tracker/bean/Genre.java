package com.example.reading_tracker.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Entity name must be provided.")
    @Size(min=1)
    public String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Saga> saga;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;

    public Set<Saga> getSaga() {
        return saga;
    }

    public void setSaga(Set<Saga> saga) {
        this.saga = saga;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getGenre_name() {
        return name;
    }

    public void setGenre_name(String genre_name) {
        this.name = genre_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
