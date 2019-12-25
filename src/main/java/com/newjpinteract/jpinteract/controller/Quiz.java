package com.newjpinteract.jpinteract.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Quiz {
    @PostMapping(value="/quiz")
    public void quizRetrieve(HttpServletRequest request){
        System.out.println(request.getParameter("quizID"));
    }
}
