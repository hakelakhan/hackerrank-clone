package com.lakhan.restprojects.hackerrankclone.models;

import javax.persistence.Entity;

@Entity
public class McqQuestion extends Question{
    private String title;
    private String[] choices;

}
