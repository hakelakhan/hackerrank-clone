package com.lakhan.restprojects.hackerrankclone.dtos;

import com.lakhan.restprojects.hackerrankclone.enums.Badge;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class ProfileInformationResponseDto {
    UpdableProfileInfo updatable;
    private String email;
    private List<String> programmingSkills;
    private int solvedProblems;
    private double score;
    private Badge badge;

}
