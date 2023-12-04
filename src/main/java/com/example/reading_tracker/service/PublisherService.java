package com.example.reading_tracker.service;

import com.example.reading_tracker.bean.Publisher;
import com.example.reading_tracker.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    public Publisher addAPublisher(String publisher_name){
        Publisher publisher = new Publisher();
        publisher.setName(publisher_name);
        return publisherRepository.save(publisher);
    }

    public boolean checkPublisherByName(String publisher_name){
        if((publisherRepository.findByName(publisher_name) != null) || (publisherRepository.findByName(publisher_name.toUpperCase()) != null)){
            return true;
        }
        return false;
    }

    public Publisher findPublisher(String publisher_name){
        return publisherRepository.findByName(publisher_name);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
}
