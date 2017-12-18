package com.baeldung.JiraAutomation.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.JiraAutomation.model.TaskStatusRating;

@Repository("taskStatusRatingRepository")
public interface TaskStatusRatingRepository extends JpaRepository<TaskStatusRating, Serializable> {

}
