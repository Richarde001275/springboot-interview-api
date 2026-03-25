package com.example.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interview.exception.TaskNotFoundException;
import com.example.interview.model.Task;

import com.example.interview.repository.TaskRepository;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	//This method will get all tasks
	@Override
	public List<Task> getAllTasks(){
		return taskRepo.findAll();
	}
	
	//This method will get task by ID
	@Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepo.findById(id);
      //If true then return the task at the id given.
        if (task.isPresent()) {
            return task.get();
        } else {
        	// Throw custom exception if task not found
        	throw new TaskNotFoundException(id);
        }
    }
	
	//This function will set the duedate 5 business dats from the now date.
    public LocalDate addSLAtoDueDate(LocalDate startDate) {
    	LocalDate date = startDate;
    	int numOfDaysAdded = 0;
    	int numOfSLADays =5 ;
    	
    	while (numOfDaysAdded < numOfSLADays) {
    		//add 1 day to date to move it forward
    		date = date.plusDays(1);
    		//Here im checking that the day does NOT equal saturday or sunday. 
			if(date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
				//add 1 day to days added till you break out of the while loop
				numOfDaysAdded++;
				
			}
			
		}
    	return date;
    	
    }
	
	//This method will create a new task 
	 @Override
	    public Task createTask(Task task) {
		//set the created on date to todays date.
		 task.setCreatedOn(LocalDate.now());
		 
		 //I want the duedate to be auto set when a task is created and with the help of my function addSLAtoDueDate it will be 
		 //5 days from the created on date. 
		 task.setDueDate(addSLAtoDueDate(task.getCreatedOn()));
		 
	        return taskRepo.save(task);
	    }
	 
	 // This method wil update an existing task
	    @Override
	    public Task updateTask(Long id, Task taskData) {
	        Task taskAlreadyExists = getTaskById(id); // Reuse get logic

	        taskAlreadyExists.setTitle(taskData.getTitle());
	        taskAlreadyExists.setDescription(taskData.getDescription());
	        taskAlreadyExists.setDueDate(taskData.getDueDate());
	        taskAlreadyExists.setPriority(taskData.getPriority());
	        taskAlreadyExists.setStatus(taskData.getStatus());
	        taskAlreadyExists.setAssignedUserId(taskData.getAssignedUserId());

	        return taskRepo.save(taskAlreadyExists);
	    }
	    
	    // This method deletes a task
	    @Override
	    public void deleteTask(Long id) {
	        Task task = getTaskById(id); 
	        taskRepo.delete(task);
	    }
	    
}
