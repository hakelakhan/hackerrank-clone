package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.CodingQuestionRepository;
import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CodingQuestionService {

    private final CodingQuestionRepository codingQuestionRepository;

    public void add(CodingQuestion question) {
        codingQuestionRepository.save(question);
    }

    public List<CodingQuestion> findAll() {
        return codingQuestionRepository.findAll();
    }

    public Optional<CodingQuestion> find(int id) {
        return codingQuestionRepository.findById((long)id);
    }

    public void addAll(List<CodingQuestion> questions) {
        codingQuestionRepository.saveAll(questions);
    }

    public void updateCodignQuestionForCurrentSubmission(CodingQuestion codingQuestion) {
        codingQuestionRepository.save(codingQuestion);
    }
}
