package com.example.UserDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UserDemoApplication.class, args);
        MessageController messageController = context.getBean(MessageController.class);
        MessageLogger messageLogger = context.getBean(MessageLogger.class);



    }
}
