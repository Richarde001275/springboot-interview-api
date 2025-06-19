package com.example.interview.service;

import java.util.List;

import com.example.interview.model.Task;

public interface TaskService {
	//Services for my service implementation class.
	List<Task> getAllTasks();
	Task getTaskById(Long id);
	Task createTask(Task task);
	Task updateTask(Long id, Task task);
	void deleteTask(Long id);

}
