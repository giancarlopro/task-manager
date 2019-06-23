package com.giancarlo.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
	
	@GetMapping("/tasks")
	public String index() {
		return "tasks/index";
	}
}
