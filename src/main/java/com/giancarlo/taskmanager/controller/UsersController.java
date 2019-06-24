package com.giancarlo.taskmanager.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giancarlo.taskmanager.model.User;
import com.giancarlo.taskmanager.repository.Users;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Users users;
	
	@PostMapping("/login")
	public String login(
		@RequestParam("email") String email,
		@RequestParam("senha") String senha,
		HttpServletResponse response,
		RedirectAttributes attrs
	) {
		User user = users.findByEmail(email);
		if (user != null && user.auth(senha)) {
			Cookie cookie = new Cookie("token", user.generateToken(users));
			cookie.setMaxAge(86400);
			cookie.setPath("/");
			cookie.setDomain("localhost");
			response.addCookie(cookie);
			attrs.addFlashAttribute("success", "Usuário logado!");
			return "redirect:/tasks";
		} else {
			attrs.addFlashAttribute("error", "Não foi possível fazer login!");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setDomain("localhost");
		response.addCookie(cookie);
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(User user, RedirectAttributes attrs) {
		users.save(user);
		
		attrs.addFlashAttribute("success", "Agora você já pode fazer login!");
		return "redirect:/";
	}
}
