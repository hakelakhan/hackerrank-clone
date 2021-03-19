package com.lakhan.restprojects.hackerrankclone.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class TestcaseExecutionDetails {
    @Id @GeneratedValue
    long id;
//TODO
    @ManyToMany
    Testcase testcase;
    int timeToRun;
    boolean passed;
    String errorMessage;
}
