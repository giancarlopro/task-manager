package com.giancarlo.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		@CookieValue(value="token", defaultValue="") String token,
		RedirectAttributes attrs
	) {
		User user = users.findByToken(token);
		if(token.isEmpty() || user == null) {
			attrs.addFlashAttribute("error", "Você precisa fazer login!");
			return new ModelAndView("redirect:/");
		}
		ModelAndView mv = new ModelAndView("tasks/index");
		mv.addObject("tasks", tasks.findByAtivo(true));
		mv.addObject("task", new Task());
		mv.addObject("user", user);
		return mv;
	}
	
	@PostMapping
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
