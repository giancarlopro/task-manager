package com.giancarlo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giancarlo.taskmanager.model.Attachment;

public interface Attachments extends JpaRepository<Attachment, Long>{

}
