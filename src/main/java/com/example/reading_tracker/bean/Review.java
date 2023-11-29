package com.example.reading_tracker.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numerical_rating")
    @NotEmpty(message = "Entity name must be provided.")
    @Min(0)
    @Max(5)
    private int numerical_rating;

    @Column(name = "final_comment")
    @NotEmpty(message = "Entity name must be provided.")
    @Size(min=1)
    private String final_comment;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

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

    public int getNumerical_rating() {
        return numerical_rating;
    }

    public void setNumerical_rating(int numerical_rating) {
        this.numerical_rating = numerical_rating;
    }

    public String getFinal_comment() {
        return final_comment;
    }

    public void setFinal_comment(String final_comment) {
        this.final_comment = final_comment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
