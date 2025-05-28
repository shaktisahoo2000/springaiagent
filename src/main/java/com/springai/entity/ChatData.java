package com.springai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ChatData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 1000)
    private String prompt;
    
    @Column(length = 1000)
    private String response;

    public ChatData() {}

    public ChatData(String prompt, String response) {
        this.prompt = prompt;
        this.response = response;
    }

    public Long getId() { return id; }
    public String getPrompt() { return prompt; }
    public String getResponse() { return response; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    public void setResponse(String response) { this.response = response; }
}
