package com.baeldung.jira.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_status_rating")
public class TaskStatusRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String taskStage;

    Double rating;

    public String getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(String taskStage) {
        this.taskStage = taskStage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    
}
