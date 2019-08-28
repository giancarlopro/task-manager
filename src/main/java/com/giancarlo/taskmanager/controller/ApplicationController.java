package com.giancarlo.taskmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giancarlo.taskmanager.model.User;

@Controller
public class ApplicationController {
	
	@GetMapping("/")
	public ModelAndView home(
		RedirectAttributes attrs,
		Authentication authentication
	) {
		if(authentication != null && authentication.isAuthenticated()) {
			return new ModelAndView("redirect:/tasks");
		}
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("user", new User());
		return mv;
	}
}
