package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;
import com.lakhan.restprojects.hackerrankclone.services.CodingQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/questions/")
public class QuestionsController {

    private final CodingQuestionService codingQuestionService;

    @PostMapping("coding-question/add-all")
    public ResponseEntity<String> addCodingQuestions(@Valid @RequestBody List<CodingQuestion> questions) {
        codingQuestionService.addAll(questions);
        return ResponseEntity.ok().body("{ \"message\" : \"Questions saved \"}");
    }

    @PostMapping("coding-question/add")
    public ResponseEntity<String> addCodingQuestion(@Valid @RequestBody CodingQuestion question) {
        codingQuestionService.add(question);
        return ResponseEntity.ok().body("{ \"message\" : \"Question saved \"}");
    }

    @GetMapping("coding-question/get")
    List<CodingQuestion> getAllCodingQuestions() {
        return codingQuestionService.findAll();
    }

    @GetMapping("coding-question/get/{id}")
    CodingQuestion getCodingQuestionById(@PathVariable int id) {
        Optional<CodingQuestion> question = codingQuestionService.find(id);
        if(question.isPresent())
            return question.get();
        else
            return null;
    }
}
