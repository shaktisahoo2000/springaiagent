package com.springai.service;

import java.util.List;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import com.springai.entity.ChatData;
import com.springai.repository.ChatRepository;

@Service
public class OllamaService {

    private final OllamaChatModel ollamaChatModel;
    private final ChatRepository chatRepository;

    public OllamaService(OllamaChatModel ollamaChatModel, ChatRepository chatRepository) {
        this.ollamaChatModel = ollamaChatModel;
        this.chatRepository = chatRepository;
    }

    public String trainAndQueryOllama(String prompt, String expectedResponse) {
        // Save data in MySQL for future reference
        ChatData newEntry = new ChatData(prompt, expectedResponse != null ? expectedResponse : "No predefined answer.");
        if(prompt != null && expectedResponse != null) {
        	chatRepository.save(newEntry);	
        }
        

        // Prepare past stored data for Ollama
        List<ChatData> trainingData = chatRepository.findAll();
        StringBuilder knowledgeBase = new StringBuilder();

//        for (ChatData entry : trainingData) {
//            knowledgeBase.append("User: ").append(entry.getPrompt()).append("\n");
//            knowledgeBase.append("Ollama: ").append(entry.getResponse()).append("\n");
//            knowledgeBase.append("---\n");
//        }
        
        for (ChatData entry : trainingData) {
            knowledgeBase.append("Stored Data:\n");
            knowledgeBase.append("User Query: ").append(entry.getPrompt()).append("\n");
            knowledgeBase.append("Relevant Answer: ").append(entry.getResponse()).append("\n");
            knowledgeBase.append("---\n");
        }

        // Call Ollama with combined stored knowledge + new input
        //String fullPrompt = "Database Knowledge:\n" + knowledgeBase + "\nNew Query:\n" + prompt;
       String fullPrompt = "Use the following stored knowledge to answer the query:\n" + knowledgeBase.toString() + "\nUser Query:\n" + prompt;
        return ollamaChatModel.call(fullPrompt);
    }
}
