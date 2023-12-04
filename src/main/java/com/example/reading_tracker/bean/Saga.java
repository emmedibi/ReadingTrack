package com.example.reading_tracker.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Saga")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Entity name must be provided.")
    @Size(min=1)
    public String name;

    @Column(name = "volumes")
    @NotNull()
    @Min(1)
    private int volumes;

    @OneToMany(mappedBy = "saga", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Book> books;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name= "saga_genre",
            joinColumns = @JoinColumn(name = "saga_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;


    public String getSaga_name() {
        return name;
    }

    public void setSaga_name(String saga_name) {
        this.name = saga_name;
    }

    public int getSaga_volume() {
        return volumes;
    }

    public void setSaga_volume(int saga_volume) {
        this.volumes = saga_volume;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
