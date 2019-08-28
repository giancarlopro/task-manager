package com.giancarlo.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giancarlo.taskmanager.model.Task;

public interface Tasks extends JpaRepository<Task, Long>{
	List<Task> findByAtivo(boolean ativo);
	List<Task> findByUserIdAndAtivoTrue(Long userId);
}
