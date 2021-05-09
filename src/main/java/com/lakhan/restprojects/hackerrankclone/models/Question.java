package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.DifficultyLevel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @NotNull
    private DifficultyLevel difficultyLevel;

    @ManyToOne
    private User postedByUser;

    @NotNull
    private long maxScore;
}
