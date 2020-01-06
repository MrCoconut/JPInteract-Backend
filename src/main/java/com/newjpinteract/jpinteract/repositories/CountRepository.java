package com.newjpinteract.jpinteract.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CountRepository extends MongoRepository<Count, String> {
    List<Count> findAll();
}
