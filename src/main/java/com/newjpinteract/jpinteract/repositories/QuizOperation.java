package com.newjpinteract.jpinteract.repositories;

import com.mongodb.annotations.Beta;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizOperation {
    @Autowired
    private QuizRepository repository;

    public void quizUpdate(JSONObject detail, String quizID) {
        Quiz quiz = repository.findByQuizID(Integer.parseInt(quizID)).get(0);
        quiz.setAttempt(quiz.getAttempt() + 1);
        detail.keySet().forEach(key -> {
            JSONObject question = new JSONObject(quiz.getQuestions().get(key));
            System.out.println(question.get("type"));
            if (question.getString("type").equals("MUL")) {
                JSONObject optionCounts = question.getJSONObject("optionCounts");
                optionCounts.put((String) detail.get(key), Integer.toString(optionCounts.getInt((String) detail.get(key)) + 1));
                question.put("optionCounts", optionCounts);
                quiz.getQuestions().put(key, question.toString());
            } else if (question.getString("type").equals("SHT")) {
                JSONArray answers = question.getJSONArray("answers");
                answers.put(detail.get(key));
                quiz.getQuestions().put(key,question.toString());
            }
        });
        repository.save(quiz);
    }
}

