package com.example.springbootform;

import com.example.springbootform.initialdata.Initial;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbooformApplication {

    public static void main(String[] args) {


        SpringApplication.run(new Class[]{SpringbooformApplication.class, Initial.class}, args);
    }

}
