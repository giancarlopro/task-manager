package com.giancarlo.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.giancarlo.taskmanager.model.Task;
import com.giancarlo.taskmanager.repository.Tasks;

@Controller
@RequestMapping("/tasks")
public class TasksController {
	
	@Autowired
	private Tasks tasks;
	
	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("tasks/index");
		mv.addObject("tasks", tasks.findByAtivo(true));
		mv.addObject("task", new Task());
		return mv;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String save(Task task) {
		task.setAtivo(true);
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
