package com.example.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interview.model.Task;
import com.example.interview.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks") //task base path endpoint
public class TaskController {
	
	@Autowired
    private TaskService taskService;
	
	//GET all tasks
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(){
		List<Task> tasks = taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
		
	}
	
	//GET task by id
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id){
		Task task = taskService.getTaskById(id);
		return ResponseEntity.ok(task);
	}
	
	//POST to create a task
	@PostMapping
	public ResponseEntity<Task> creatTask(@Valid @RequestBody Task task){
		Task taskCreate = taskService.createTask(task);
		return ResponseEntity.ok(taskCreate);
	}
	
	//PUT to update a task
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @Valid @RequestBody Task task){
		Task updateTask = taskService.updateTask(id, task);
		return ResponseEntity.ok(updateTask);
	}
	
	//DELETE the task
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build(); //This will send back a 204 message indicating a success with no content to return
	}




}
