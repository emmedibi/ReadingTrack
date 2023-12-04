package com.example.reading_tracker.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "comment_text")
    @Size(min=1)
    private String comment_text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "comment_date") // data di publicazione
    private LocalDate comment_date;

    @Column(name = "numerical_rating")
    @NotEmpty(message = "Entity name must be provided.")
    @Min(0)
    @Max(5)
    private int numerical_rating;

    @Column(name = "reading_progress")
    @NotEmpty(message = "Entity name must be provided.")
    @Min(0)
    private int reading_progress;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    // GETTER AND SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public LocalDate getComment_date() {
        return comment_date;
    }

    public void setComment_date(LocalDate comment_date) {
        this.comment_date = comment_date;
    }

    public int getNumerical_rating() {
        return numerical_rating;
    }

    public void setNumerical_rating(int numerical_rating) {
        this.numerical_rating = numerical_rating;
    }

    public int getReading_progress() {
        return reading_progress;
    }

    public void setReading_progress(int reading_progress) {
        this.reading_progress = reading_progress;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

/*    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }*/
}
