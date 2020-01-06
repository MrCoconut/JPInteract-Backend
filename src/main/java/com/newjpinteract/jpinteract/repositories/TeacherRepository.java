package com.newjpinteract.jpinteract.repositories;

import netscape.javascript.JSObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
    List<Teacher> findByName(@Param("Name") String name);
}
