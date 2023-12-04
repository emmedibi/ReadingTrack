package com.example.reading_tracker.service;

import com.example.reading_tracker.bean.Genre;
import com.example.reading_tracker.bean.Publisher;
import com.example.reading_tracker.bean.Saga;
import com.example.reading_tracker.repository.SagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SagaService {
    @Autowired
    SagaRepository sagaRepository;

    public boolean checkSagaName(String saga_name){
        if((sagaRepository.findByName(saga_name) != null) || (sagaRepository.findByName(saga_name.toUpperCase()) != null)){
            return true;
        }
        return false;
    }

    public Saga findSaga(String saga_name){
        return sagaRepository.findByName(saga_name);
    }

    public Saga addSaga(String saga_name, Collection<Genre> genres, int volumes){
        Saga saga = new Saga();
        saga.setSaga_name(saga_name);
        saga.setGenres(genres);
        saga.setSaga_volume(volumes);
        System.out.println(saga.getSaga_name() + " " + saga.getSaga_volume());
        for(Genre g : saga.getGenres()){
            System.out.println(g.getGenre_name());
        }
        return sagaRepository.save(saga);
    }

    public List<Saga> getAllSaga() {
        return sagaRepository.findAll();
    }
}
