package com.giancarlo.taskmanager.model;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.giancarlo.taskmanager.repository.Users;

import javax.persistence.GenerationType;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(unique = true, length = 35)
	private String email;

	private String password;
	
	private String token;
	
	@OneToMany(mappedBy = "user")
	private List<Task> tasks;
	
	public boolean auth(String auth_password) {
		if (this.password.equals(auth_password))
			return true;
		return false;
	}
	
	public String generateToken(Users userRepo) {
		Date currentDate = new Date();
		String tokenString = this.nome + this.email + currentDate.toString();
		this.token = Base64.getEncoder().encodeToString(tokenString.getBytes());
		userRepo.save(this);
		return this.token;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
