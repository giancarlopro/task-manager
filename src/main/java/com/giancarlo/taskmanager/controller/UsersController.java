package com.giancarlo.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import com.giancarlo.taskmanager.model.User;
import com.giancarlo.taskmanager.repository.Users;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Users users;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("users/index");
		mv.addObject("users", users.findAll());
		mv.addObject("user", new User());
		return mv;
	}

	@PostMapping
	public String create(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/users";
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		this.users.save(user);
		return "redirect:/users";
	}

	@GetMapping("/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("users/index");
		mv.addObject("user", users.getOne(id));
		return mv;
	}

	@PostMapping("/register")
	public String register(User user, RedirectAttributes attrs) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setAuthority("VIEWR");
		users.save(user);
		attrs.addFlashAttribute("success", "Agora você já pode fazer login!");
		return "redirect:/";
	}

	@GetMapping("/{id}/done")
	public String done(@PathVariable Long id) {
		users.deleteById(id);
		return "redirect:/users";
	}
}
