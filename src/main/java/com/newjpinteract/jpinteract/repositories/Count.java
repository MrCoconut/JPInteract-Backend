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
@Document(collection = "quizCount")
public class Count {
    @Id
    private String id;
    private int count;
}
