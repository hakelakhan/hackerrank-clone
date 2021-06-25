package com.lakhan.restprojects.hackerrankclone.dtos;

import lombok.Data;

@Data
public class UpdableProfileInfo {
    private String fullName;
    private String mobile;
    private String qualification;
    private double cgpa;
    private String university;
    private int passedOutYear;
    private String profession;
    private int experienceInYears;
    private String organization;
    private String additionalDetails;
    private String linkedInProfile;
    private String facebookProfile;
    private String githubProfile;
}
