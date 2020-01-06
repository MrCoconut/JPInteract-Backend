package com.newjpinteract.jpinteract.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountOperation {
    @Autowired
    private CountRepository repository;

    public int retrieveCount(){
        Count quizCount = repository.findAll().get(0);
        int count = quizCount.getCount();
        quizCount.setCount(quizCount.getCount() + 1);
        repository.save(quizCount);
        return count;
    }

}
