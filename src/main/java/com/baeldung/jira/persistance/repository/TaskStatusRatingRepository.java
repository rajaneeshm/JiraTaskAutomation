package com.baeldung.jira.persistance.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.jira.model.TaskStatusRating;

@Repository("taskStatusRatingRepository")
public interface TaskStatusRatingRepository extends JpaRepository<TaskStatusRating, Serializable> {

    TaskStatusRating findByTaskStage(String taskStage);

}
