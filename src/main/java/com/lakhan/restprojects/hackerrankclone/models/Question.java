package com.lakhan.restprojects.hackerrankclone.models;

import com.lakhan.restprojects.hackerrankclone.enums.DifficultyLevel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@MappedSuperclass
public class Question {
    @Id
    @GeneratedValue
    private int questionId;

    @NotNull
    private DifficultyLevel difficultyLevel;

    @ManyToOne
    private User postedByUser;

    @NotNull
    private long maxScore;

    @ManyToMany
    List<Topic> associatedTopics;

}
