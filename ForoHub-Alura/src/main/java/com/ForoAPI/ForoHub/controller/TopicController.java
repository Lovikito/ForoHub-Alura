package com.ForoAPI.ForoHub.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ForoAPI.ForoHub.domain.DatosTopic;
import com.ForoAPI.ForoHub.domain.Topic;
import com.ForoAPI.ForoHub.domain.TopicService;
import com.ForoAPI.ForoHub.infra.security.CustomConflictException;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    public TopicService topicService;
    @GetMapping
    public ResponseEntity<List<DatosTopic>> getAllTopics() {
        List<DatosTopic> topicsDTO = topicService.getAllTopics();
        return new ResponseEntity<>(topicsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosTopic> getTopicById(@PathVariable Long id) {
        try {
        	DatosTopic datosTopic = topicService.getTopicById(id);
            return new ResponseEntity<>(datosTopic, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody DatosTopic dto) {
        try {
            Topic createdTopic = topicService.createTopic(dto);
            return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
        } catch (CustomConflictException e) {
             return new ResponseEntity<>(e.getStatus());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody DatosTopic datos) {
        try {
            Topic updatedTopic = topicService.updateTopic(id, datos);
            return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
        } catch (CustomConflictException e) {
            return new ResponseEntity<>(e.getStatus());
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
