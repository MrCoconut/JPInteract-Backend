package com.newjpinteract.jpinteract.repositories;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "quiz")
public class Quiz {
    @Id
    private String id;
    private String name;
    private int attempt;
    private int quizID;
    private HashMap<String, String> questions;
}
