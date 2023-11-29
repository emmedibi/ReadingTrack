package com.example.reading_tracker.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;

@Entity
@Table(name = "Bookshelf")
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Entity name must be provided.")
    @Size(min=1)
    private String name;

    @Column(name = "exclusive")
    @NotNull
    private Boolean exclusive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name= "bookshelf_book",
            joinColumns = @JoinColumn(name = "bookshelf_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnore
    private Collection<Book> books;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // GETTER AND SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
