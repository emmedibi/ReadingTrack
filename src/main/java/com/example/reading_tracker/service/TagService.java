package com.example.reading_tracker.service;

import com.example.reading_tracker.bean.Tag;
import com.example.reading_tracker.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public boolean checkTagName(String tag_name){
        if((tagRepository.findByName(tag_name) != null) || (tagRepository.findByName(tag_name.toUpperCase()) != null)){
            return true;
        }
        return false;
    }

    public Tag addATag(String tag_name){
        Tag tag = new Tag();
        tag.setTag_name(tag_name);
        return tagRepository.save(tag);
    }

    public Tag findTag(String tag_name){
        return tagRepository.findByName(tag_name);
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
