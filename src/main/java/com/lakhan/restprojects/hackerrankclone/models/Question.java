package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.DifficultyLevel;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Question {
    @Id
    @GeneratedValue
    private long questionId;

    @NotNull
    private DifficultyLevel difficultyLevel;

    @ManyToOne
    private User postedByUser;

    @NotNull
    private long maxScore;
}
