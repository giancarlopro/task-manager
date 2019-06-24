package com.giancarlo.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giancarlo.taskmanager.model.User;
import com.giancarlo.taskmanager.repository.Users;

@Controller
public class ApplicationController {
	
	@Autowired
	private Users users;
	
	@GetMapping("/")
	public ModelAndView home(
		@CookieValue(value="token", defaultValue="") String token,
		RedirectAttributes attrs
	) {
		if(!token.isEmpty() && users.findByToken(token) != null) {
			attrs.addFlashAttribute("info", "Você já está logado!");
			return new ModelAndView("redirect:/tasks");
		}
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("user", new User());
		return mv;
	}
}
