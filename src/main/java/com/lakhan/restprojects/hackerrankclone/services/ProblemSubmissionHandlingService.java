package com.lakhan.restprojects.hackerrankclone.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.CodeEvaluationResponse;
import com.lakhan.restprojects.hackerrankclone.dtos.JdoodleCodeEvaluationRequest;
import com.lakhan.restprojects.hackerrankclone.dtos.JdoodleCodeEvaluationResponse;
import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;
import com.lakhan.restprojects.hackerrankclone.models.Testcase;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProblemSubmissionHandlingService {

    @Autowired
    JdoodleApiService jdoodleApiService;

    @Autowired
    CodingQuestionService codingQuestionService;

    @SneakyThrows
    public CodeEvaluationResponse submitCodeForEvaluation(CodeEvaluationRequest requestDto) {
        ObjectMapper objectMapper = new ObjectMapper();
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
            log.info(codingQuestion.toString());
            for (int counter = 0; counter < codingQuestion.getTestcases().size(); counter++) {
                Testcase testcase = codingQuestion.getTestcases().get(counter);
                String requestString = "";
                try {
                    requestString = objectMapper.writeValueAsString(requestBuilder.stdin(testcase.getProvidedInput()).build());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();        //TODO remove this part
                }
                JdoodleCodeEvaluationResponse response = jdoodleApiService.submitCodeForEvaluation(requestString);
                if(isCompilationFailure(response)) {
                    responseBuilder.compilationErrorMessage(response.getOutput());
                    return responseBuilder.build();
                }
                responseBuilder.testcaseResult(buildTestcaseResult(response, testcase).id(counter).build());
            }
            return responseBuilder.build();
        }
        else {
            log.error("Could not find question with id " + requestDto.getQuestionId());
        }
        return null;
    }

    private CodeEvaluationResponse.TestcaseResult.TestcaseResultBuilder buildTestcaseResult(JdoodleCodeEvaluationResponse response, Testcase testcase) {
        return CodeEvaluationResponse.TestcaseResult.builder()
                .memory(response.getMemory())
                .cpuTime(response.getCpuTime())
                .output(response.getOutput())
                .input(testcase.getProvidedInput())
                .testcasePassed(testcase.passes(response.getOutput()));
    }

    private boolean isCompilationFailure(JdoodleCodeEvaluationResponse response) {
        return response.getMemory() == null || response.getCpuTime() == null;
    }
}
