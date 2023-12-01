package com.example.UserDemo;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class MessageLogger {

    private final String logFilePath = "log.txt";

    public void logActivity(String message) {
        try {
            FileWriter writer = new FileWriter(logFilePath, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

