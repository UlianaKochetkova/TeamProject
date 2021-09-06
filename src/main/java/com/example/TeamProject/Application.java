package com.example.TeamProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import science.NLPManager;

import java.util.List;

@SpringBootApplication
public class Application {

    public static NLPManager nlpManager;

    public static List<String> messages;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        nlpManager = new NLPManager();

        for (String message : messages) {
            nlpManager.addMessage(message);
        }
    }

}
