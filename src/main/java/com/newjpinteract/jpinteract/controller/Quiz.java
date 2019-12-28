package com.newjpinteract.jpinteract.controller;

import com.newjpinteract.jpinteract.repositories.QuizRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Quiz {
    @Autowired
    private QuizRepository repository;

    @RequestMapping("/quiz")
    public String quizRetrieve(@RequestParam(value = "quizID") String quizID) {
        JSONObject response = new JSONObject();
        try {
            List<com.newjpinteract.jpinteract.repositories.Quiz> result = repository.findByQuizID(Integer.parseInt(quizID));
            if (result.isEmpty()) {
                response.put("result", 404);
            } else {
                response.put("result", 200);
                response.put("name", result.get(0).getName());
                response.put("questions", result.get(0).getQuestions());
            }
        } catch (Exception e) {
            response.put("result", 500);
        }
        return response.toString();
    }
}
