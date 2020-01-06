package com.newjpinteract.jpinteract.controller;

import com.newjpinteract.jpinteract.repositories.*;
import com.newjpinteract.jpinteract.repositories.Quiz;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teacher {
    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherOperation operation;

    @Autowired
    private QuizRepository quizRepo;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpEntity<String> incomePost) {
        String json = incomePost.getBody();
        JSONObject detail = new JSONObject(json);
        JSONObject response = new JSONObject();
        if(operation.login(detail))
            response.put("result",200);
        else
            response.put("result", 404);
        return response.toString();
    }

    @RequestMapping("/quizRetrieve")
    public String quizRetrieve(@RequestParam(value = "name") String name) {
        com.newjpinteract.jpinteract.repositories.Teacher teacher = repository.findByName(name).get(0);
        JSONObject response = new JSONObject();
        teacher.getQuiz().forEach(number->{
           Quiz quiz = quizRepo.findByQuizID(Integer.parseInt(number)).get(0);
            JSONObject json = new JSONObject();
            json.put("name", quiz.getName());
            json.put("quizID", quiz.getQuizID());
            json.put("attempt", quiz.getAttempt());
            json.put("questions", quiz.getQuestions());
            response.put(number, json);
        });
        return response.toString();
    }
}
