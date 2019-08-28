package com.giancarlo.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import com.giancarlo.taskmanager.model.Task;
import com.giancarlo.taskmanager.model.User;
import com.giancarlo.taskmanager.repository.Tasks;
import com.giancarlo.taskmanager.repository.Users;

@Controller
@RequestMapping("/tasks")
public class TasksController {
	
	@Autowired
	private Tasks tasks;
	
	@Autowired
	private Users users;
	
	@GetMapping
	public ModelAndView index(
		RedirectAttributes attrs,
		Authentication authentication
	) {
		ModelAndView mv = new ModelAndView("tasks/index");

		User user = users.findByEmail(authentication.getName());
		List<Task> allTasks = tasks.findByUserIdAndAtivoTrue(user.getId());
		mv.addObject("tasks", allTasks == null? new ArrayList<>(): allTasks);
		mv.addObject("task", new Task());
		return mv;
	}
	
	@PostMapping
	public String save(Task task, Authentication authentication) {
		task.setAtivo(true);
		task.setUser(
			users.findByEmail(authentication.getName())
		);
		tasks.save(task);
		return "redirect:/tasks";
	}
	
	@RequestMapping(value="/{id}/done")
	public String done(@PathVariable Long id) {
		Task task = tasks.getOne(id);
		task.setAtivo(false);
		tasks.save(task);
		return "redirect:/tasks";
	}
}
