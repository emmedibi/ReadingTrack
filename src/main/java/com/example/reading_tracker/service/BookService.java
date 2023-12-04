package com.example.reading_tracker.service;

import com.example.reading_tracker.bean.Book;
import com.example.reading_tracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book){
        return bookRepository.save(book);
    }
}
