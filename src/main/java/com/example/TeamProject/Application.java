package com.example.TeamProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import science.NLPManager;

@SpringBootApplication
public class Application {

    public static NLPManager nlpManager;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        nlpManager = new NLPManager();
    }

}
