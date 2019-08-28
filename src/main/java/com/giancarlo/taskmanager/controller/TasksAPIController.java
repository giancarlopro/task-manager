package com.giancarlo.taskmanager.controller;

import com.giancarlo.taskmanager.repository.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.giancarlo.taskmanager.model.Task;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TasksAPIController {

    @Autowired
    private Tasks tasksRepository;

    @GetMapping
    public List<Task> index() {
        return tasksRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task show(@PathVariable("id") Long id) {
        return tasksRepository.getOne(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return tasksRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task updateTask) {
        return tasksRepository.findById(id).map(task -> {
            task.setAtivo(updateTask.isAtivo());
            task.setAttachment(updateTask.getAttachment());
            task.setDescricao(updateTask.getDescricao());
            task.setNome(updateTask.getNome());
            task.setUser(updateTask.getUser());
            return tasksRepository.save(task);
        }).orElseGet(() -> {
            updateTask.setId(id);
            return tasksRepository.save(updateTask);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tasksRepository.deleteById(id);
    }

    @GetMapping("/{id}/done")
    public void done(@PathVariable Long id) {
        // Task task = tasksRepository.getOne(id);
        tasksRepository.findById(id).map(task -> {
            task.setAtivo(false);
            return tasksRepository.save(task);
        });
    }
}
