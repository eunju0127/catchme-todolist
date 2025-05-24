package com.catchme.todolist.todolist.repository;

import com.catchme.todolist.todolist.entity.TodolistEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodolistRepository extends JpaRepository<TodolistEntity, Long> {
    List<TodolistEntity> findByIsDeletedFalse();
    Optional<TodolistEntity> findByIdAndIsDeletedFalse(Long id);
}
