package com.paway.spring.data.kmoneta.user.repository;
import com.paway.spring.data.kmoneta.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}