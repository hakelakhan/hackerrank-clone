package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CodeSubmissionDetails {

    @Id @GeneratedValue
    long submissionId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    User solvedBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    CodingQuestion codingQuestion;

    String codeSubmitted;

    String codeSubmittedLanguage;

    Double score;

    public static CodeSubmissionDetails getPreviousBestSubmission(User user, CodingQuestion question) {
        return user.getSubmissionDetails().stream().filter(detail -> detail.getCodingQuestion().equals(question)).max(Comparator.comparingDouble(CodeSubmissionDetails::getScore)).orElse(null);
    }
}
