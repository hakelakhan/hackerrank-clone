package com.lakhan.restprojects.hackerrankclone.controllers;

import com.lakhan.restprojects.hackerrankclone.dtos.CodingQuestionDetailsDto;
import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;
import com.lakhan.restprojects.hackerrankclone.models.User;
import com.lakhan.restprojects.hackerrankclone.services.CodingQuestionService;
import com.lakhan.restprojects.hackerrankclone.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
@RequestMapping("/api/questions/")
public class QuestionsController {

    private final CodingQuestionService codingQuestionService;

    private final UserDetailsServiceImpl userDetailsService;

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
    List<CodingQuestionDetailsDto> getAllCodingQuestions() {
        return codingQuestionService.findAll().stream().map(question -> mapToDto(question)).collect(toList());
    }

    private CodingQuestionDetailsDto mapToDto(CodingQuestion question) {
        if(question == null ) return  null;
        User user = userDetailsService.getActiveUser().get();
        CodeSubmissionDetails previousBestSubmission = CodeSubmissionDetails.getPreviousBestSubmission(user, question, null);
        double score = previousBestSubmission == null ? 0 : previousBestSubmission.getScore();
        return CodingQuestionDetailsDto.builder()
                .title(question.getTitle())
                .description(question.getDescription())
                .maxScore(question.getMaxScore())
                .score(score)
                .solved(previousBestSubmission != null)
                .questionId(question.getQuestionId())
                .difficultyLevel(question.getDifficultyLevel())
                .testcases(question.getTestcases())
                .build();
    }

    @GetMapping("coding-question/get/{id}")
    ResponseEntity<CodingQuestionDetailsDto> getCodingQuestionById(@PathVariable int id) {
        Optional<CodingQuestion> questionOptional = codingQuestionService.find(id);
        if(questionOptional.isPresent()) {
            CodingQuestion question = questionOptional.get();
            return ResponseEntity.ok(mapToDto(question));
        }
        else
            return ResponseEntity.notFound().build();
    }
}
