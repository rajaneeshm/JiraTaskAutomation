package com.baeldung.JiraAutomation.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Task_Status_Rating")
public class TaskStatusRating {

	@Id
	Long id;
	
	String taskStage;
	
	Double rating;
}
