package com.catchme.todolist.todolist.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.catchme.todolist.todolist.dto.TodolistDto;
import com.catchme.todolist.todolist.entity.TodolistEntity;
import com.catchme.todolist.todolist.mapper.TodolistMapper;
import com.catchme.todolist.todolist.repository.TodolistRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TodolistService {

    private final TodolistMapper todolistMapper;
    private final TodolistRepository todolistRepository;

    @Transactional
    public TodolistDto createTodolist(TodolistDto todolistDto) {
        TodolistEntity todolistEntity = todolistMapper.toEntity(todolistDto);
        todolistRepository.save(todolistEntity);
        return todolistMapper.toDto(todolistEntity);
    }

    @Transactional(readOnly = true)
    public List<TodolistDto> findAllTodos() {
        List<TodolistEntity> todolistEntityList = todolistRepository.findByIsDeletedFalse();
        return todolistMapper.toDto(todolistEntityList);
    }

    @Transactional(readOnly = true)
    public TodolistDto findTodolistById(Long id) {
        TodolistEntity todolistEntity = todolistRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 할일입니다."));
        return todolistMapper.toDto(todolistEntity);
    }

    @Transactional
    public TodolistDto updateTodolist(Long id, TodolistDto todolistDto) {
        TodolistEntity todolistEntity = todolistRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "할일을 찾을 수 없습니다."));
        todolistEntity.update(todolistDto.getTitle(), todolistDto.getDescription(),
                todolistDto.getStartDate(), todolistDto.getDueDate());
        todolistRepository.save(todolistEntity);
        return todolistMapper.toDto(todolistEntity);
    }

    @Transactional
    public void deleteTodolist(Long id) {
        TodolistEntity todolistEntity = todolistRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "이미 삭제된 할일입니다."));
        todolistEntity.delete();
        todolistRepository.save(todolistEntity);
    }

    @Transactional
    public void completeTodolist(Long id) {
        TodolistEntity todolistEntity = todolistRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 할일입니다."));

        if (!todolistEntity.getIsCompleted()) {
            todolistEntity.setIsCompleted(true);
        }

        todolistRepository.save(todolistEntity);
    }

    @Transactional
    public void pinTodolist(Long id) {
        TodolistEntity todolistEntity = todolistRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "존재하지 않는 할일입니다."));
        if (!todolistEntity.getIsPinned()) {
            todolistEntity.setIsPinned(true);
        }

        todolistRepository.save(todolistEntity);
    }
}

