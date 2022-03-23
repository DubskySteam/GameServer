package com.dubsky.cardgame.apiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServerApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ApiServerApplication.class, args);
        } catch (Exception e) {
            System.out.println("Error with the main application");
            e.printStackTrace();
        }
    }

}
