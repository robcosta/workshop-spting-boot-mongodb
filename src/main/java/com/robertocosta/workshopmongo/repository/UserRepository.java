package com.robertocosta.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.robertocosta.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
