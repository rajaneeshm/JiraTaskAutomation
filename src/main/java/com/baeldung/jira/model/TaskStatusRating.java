package com.baeldung.jira.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Task_Status_Rating")
public class TaskStatusRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Task_Status_Rating_id")
    private Long id;

    String taskStage;

    Double rating;
}
