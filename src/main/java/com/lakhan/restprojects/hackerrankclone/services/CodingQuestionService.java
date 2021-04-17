package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.CodingQuestionRepository;
import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;

import java.util.List;
import java.util.Optional;

public class CodingQuestionService {
    CodingQuestionRepository codingQuestionRepository;
    public void add(CodingQuestion question) {
        codingQuestionRepository.save(question);
    }

    public List<CodingQuestion> findAll() {
        return codingQuestionRepository.findAll();
    }

    public Optional<CodingQuestion> find(long id) {
        return codingQuestionRepository.findById(id);
    }
}
