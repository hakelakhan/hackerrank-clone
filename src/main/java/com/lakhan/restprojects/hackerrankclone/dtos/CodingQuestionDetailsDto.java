package com.lakhan.restprojects.hackerrankclone.dtos;

import com.lakhan.restprojects.hackerrankclone.enums.DifficultyLevel;
import com.lakhan.restprojects.hackerrankclone.models.Testcase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CodingQuestionDetailsDto {
    private long questionId;
    private DifficultyLevel difficultyLevel;
    private String title;
    private String description;
    private String associatedTopics;
    private boolean solved;
    private double score;
    private double maxScore;
    private List<Testcase> testcases;

}