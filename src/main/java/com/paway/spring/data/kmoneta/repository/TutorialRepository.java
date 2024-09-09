package com.paway.spring.data.kmoneta.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.paway.spring.data.kmoneta.model.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
  List<Tutorial> findByPublished(boolean published);
  List<Tutorial> findByTitleContaining(String title);
}
