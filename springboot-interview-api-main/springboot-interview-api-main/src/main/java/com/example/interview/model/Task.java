package com.example.interview.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Task {
	
	
	@Id
	//@GeneratedValue will allow for auto increment of id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long id;
	
	//@NotBlank is a validaiton instead of doing it in the controller and checking task.getTitle() == null
	//and throwing an illegal argument exception. 
	@NotBlank(message = "Tittle is a required field.") 
	private String title;
	
	private String description;
	
	//Created on is system generated and should be read only. 
	@JsonProperty(access= Access.READ_ONLY)
	private LocalDate createdOn;
	
	//Due date is system generated and should be read only. 
	@JsonProperty(access= Access.READ_ONLY)
	private LocalDate dueDate;
	
	//I am using an Enum class here to explicitly define the priorities. options: LOW, MEDIUM, HIGH
	@Enumerated(EnumType.STRING)
	private TaskPriority priority;
	
	//I am using an Enum class here to explicitly define the status. options: TO_DO, IN_PROGRESS, DONE
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	//usernames at canada life can be letters, numbers, or both so set this to alphanumeric.
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "User ID must be alphanumeric only")
	private String assignedUserId;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the priority
	 */
	public TaskPriority getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	/**
	 * @return the assignedUserId
	 */
	public String getAssignedUserId() {
		return assignedUserId;
	}

	/**
	 * @param assignedUserId the assignedUserId to set
	 */
	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	
	
	
	

}
