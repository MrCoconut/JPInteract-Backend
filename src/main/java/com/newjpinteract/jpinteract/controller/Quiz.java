package com.newjpinteract.jpinteract.controller;

import com.newjpinteract.jpinteract.repositories.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@RestController
public class Quiz {
    @Autowired
    private QuizRepository repository;

    @Autowired
    private TeacherOperation teacherOperation;

    @Autowired
    private CountOperation countOperation;

    @Autowired
    private QuizOperation operation;

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
                response.put("quizID", result.get(0).getQuizID());
                JSONObject question = new JSONObject(result.get(0).getQuestions());
                response.put("questions", question);
            }
        } catch (Exception e) {
            response.put("result", 500);
        }
        return response.toString();
    }

    @RequestMapping(value = "/quizSubmit", method = RequestMethod.POST)
    public String quizSubmit(@RequestParam(value = "quizID") String quizID, HttpEntity<String> incomePost) {
        String json = incomePost.getBody();
        JSONObject update = new JSONObject(json);
        operation.quizUpdate(update,quizID);
        JSONObject response = new JSONObject();
        response.put("result",200);
        return response.toString();
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.PUT)
    public String quizPut(HttpEntity<String> incomePost) {
        JSONObject json = new JSONObject(incomePost.getBody());
        com.newjpinteract.jpinteract.repositories.Quiz newQuiz = new com.newjpinteract.jpinteract.repositories.Quiz();
        newQuiz.setName(json.getString("name"));
        newQuiz.setQuestions(new HashMap<String, String>());
        json.getJSONObject("questions").keySet().forEach(num->{
            JSONObject question = json.getJSONObject("questions").getJSONObject(num);
            if (question.getString("type").equals("MUL")){
                HashMap<String, Integer> optionCounts = new HashMap<String, Integer>();
                optionCounts.put("A",0);
                optionCounts.put("B",0);
                optionCounts.put("C",0);
                optionCounts.put("D",0);
                question.put("optionCounts",optionCounts);
            }else if(question.getString("type").equals("SHT")){
                Vector<String> answers = new Vector<String>();
                question.put("answers",answers);
            }
            newQuiz.getQuestions().put(num, question.toString());
        });
        newQuiz.setAttempt(0);
        int count = countOperation.retrieveCount();
        newQuiz.setQuizID(count);
        repository.save(newQuiz);
        teacherOperation.addQuizToTeacher(json.getString("teacher"), Integer.toString(count));
        JSONObject response = new JSONObject();
        response.put("result",200);
        return response.toString();
    }
}
