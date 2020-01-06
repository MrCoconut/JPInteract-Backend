package com.newjpinteract.jpinteract.repositories;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherOperation {
    @Autowired
    private TeacherRepository repository;

    public boolean login(JSONObject detail) {
        boolean isSuccess = false;
        List<Teacher> teacher = repository.findByName(detail.getString("name"));
        if (!teacher.isEmpty() && teacher.get(0).getPassword().equals(detail.getString("password")))
                isSuccess = true;
        return isSuccess;
    }

    public void addQuizToTeacher(String name, String quizID){
        Teacher teacher = repository.findByName(name).get(0);
        teacher.getQuiz().add(quizID);
        repository.save(teacher);
    }
}