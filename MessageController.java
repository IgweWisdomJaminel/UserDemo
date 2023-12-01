package com.example.UserDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {

    private final String messagesFilePath = "messages.txt";
    private final String logFilePath = "log.txt";

    @Autowired
    private MessageLogger messageLogger;

    @GetMapping("/messages")
    public List<String> getMessages() {
        return readFromFile(messagesFilePath);
    }

    @GetMapping("/messageCount")
    public int getMessageCount() {
        List<String> messages = readFromFile(messagesFilePath);
        return messages.size();
    }

    @PostMapping("/messages")
    public void postMessage(@RequestBody String message) {
        writeToMessagesFile(message);
        messageLogger.logActivity("New message created");
    }

    // Helper methods
    private List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource(filePath);
            File file = resource.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void writeToMessagesFile(String message) {
        try {
            FileWriter writer = new FileWriter(messagesFilePath, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

