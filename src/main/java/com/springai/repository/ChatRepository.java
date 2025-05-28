package com.springai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springai.entity.ChatData;

@Repository
public interface ChatRepository extends JpaRepository<ChatData, Long> {
    Optional<ChatData> findByPrompt(String prompt);
}

