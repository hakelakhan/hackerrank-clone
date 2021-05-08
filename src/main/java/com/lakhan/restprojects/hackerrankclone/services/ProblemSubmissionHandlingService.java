package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponse;
import com.lakhan.restprojects.hackerrankclone.dtos.JdoodleCodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.JdoodleCodeEvaluationResponse;
import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;
import com.lakhan.restprojects.hackerrankclone.models.Testcase;
import com.lakhan.restprojects.hackerrankclone.models.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProblemSubmissionHandlingService {

    @Autowired
    JdoodleApiService jdoodleApiService;

    @Autowired
    CodingQuestionService codingQuestionService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    CodeSubmissionDetailsService codeSubmissionDetailsService;

    @Autowired
    AuthService authService;
    @SneakyThrows
    public CodeEvaluationResponse submitCodeForEvaluation(CodeEvaluationRequest requestDto) {
        System.out.println(requestDto);
        JdoodleCodeEvaluationRequest.JdoodleCodeEvaluationRequestBuilder requestBuilder = JdoodleCodeEvaluationRequest.builder()
                .clientId("1c9b608a6226ca009f3f4fd84d84c59e")
                .clientSecret("6bf57de9e7b7bef12eb8fe4ffbcf17a5c04012d0c64ed6bd10ddc1cff9879e20")
                .script(requestDto.getSource())
                .language(requestDto.getLang())
                .versionIndex("0");

        Optional<CodingQuestion> question = codingQuestionService.find(requestDto.getQuestionId());
        CodeEvaluationResponse.CodeEvaluationResponseBuilder responseBuilder = CodeEvaluationResponse.builder();

        if(question.isPresent()) {
            CodingQuestion codingQuestion = question.get();
            int totalTestcases = codingQuestion.getTestcases().size();
            int failedTestcases = 0;
            for (int counter = 0; counter < codingQuestion.getTestcases().size(); counter++) {
                Testcase testcase = codingQuestion.getTestcases().get(counter);
                JdoodleCodeEvaluationResponse response = jdoodleApiService.submitCodeForEvaluation(requestBuilder.stdin(testcase.getProvidedInput()).build());
                if(isCompilationFailure(response)) {
                    responseBuilder.errorMessage(response.getOutput());
                    return responseBuilder.build();
                }
                CodeEvaluationResponse.TestcaseResult result = buildTestcaseResult(response, testcase).id(counter).build();
                if(!result.getTestcasePassed()) {
                    failedTestcases++;
                }
                responseBuilder.testcaseResult(result);
            }
                int passedTestcases = totalTestcases - failedTestcases;
                double score = (passedTestcases / (double) totalTestcases) * codingQuestion.getMaxScore();
                responseBuilder.passedTestcases(passedTestcases)
                        .score(score)
                        .allTestcasesPassed(passedTestcases == totalTestcases)
                        .maxScore(codingQuestion.getMaxScore())
                        .totalTestcases(totalTestcases);
                if(passedTestcases > 0) {
                    User user = userDetailsService.getActiveUser().get();
                    CodeSubmissionDetails details = new CodeSubmissionDetails();
                    details.setCodingQuestion(codingQuestion);
                    details.setScore(score);
                    details.setSolvedBy(user);
                    details.setCodeSubmitted(requestDto.getSource());
                    details.setCodeSubmittedLanguage(requestDto.getLang());
                    codeSubmissionDetailsService.add(details);
                    user = userDetailsService.updateUserRecordForCurrentCodeSubmission(user, details);
                    codingQuestion.getSubmissionDetails().add(details);
                    codingQuestionService.updateCodignQuestionForCurrentSubmission(codingQuestion);
                    responseBuilder.updatedUserScore(user.getCurrentScore());
                }
            return responseBuilder.build();
        }
        return null;
    }

    private CodeEvaluationResponse.TestcaseResult.TestcaseResultBuilder buildTestcaseResult(JdoodleCodeEvaluationResponse response, Testcase testcase) {
        return CodeEvaluationResponse.TestcaseResult.builder()
                .memory(response.getMemory())
                .cpuTime(response.getCpuTime())
                .output(response.getOutput())
                .expectedOutput(testcase.getExpectedOutput())
                .input(testcase.getProvidedInput())
                .testcasePassed(testcase.passes(response.getOutput()));

    }

    private boolean isCompilationFailure(JdoodleCodeEvaluationResponse response) {
        return response.getMemory() == null || response.getCpuTime() == null;
    }
}
