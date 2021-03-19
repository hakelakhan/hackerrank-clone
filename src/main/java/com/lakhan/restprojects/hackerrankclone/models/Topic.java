package com.lakhan.restprojects.hackerrankclone.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class Topic {
    @Id
    @GeneratedValue
    int id;

    @NotEmpty @Size(max=30)
    String shortTopicName;
}
