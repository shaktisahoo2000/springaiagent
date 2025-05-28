package com.springai.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springai.service.OllamaService;

@RestController
@RequestMapping("/ollama")
public class PromptController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/train-and-query")
    public ResponseEntity<String> trainAndQueryOllama(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        String expectedResponse = request.get("response"); // Optional

        if (prompt == null || prompt.isEmpty()) {
            return ResponseEntity.badRequest().body("Prompt cannot be empty!");
        }

        String ollamaOutput = ollamaService.trainAndQueryOllama(prompt, expectedResponse);
        return ResponseEntity.ok(ollamaOutput);
    }
}
