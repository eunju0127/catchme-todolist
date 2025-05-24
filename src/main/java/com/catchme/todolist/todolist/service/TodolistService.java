package com.catchme.todolist.todolist.service;

import com.catchme.todolist.todolist.dto.TodolistDto;
import com.catchme.todolist.todolist.entity.TodolistEntity;
import com.catchme.todolist.todolist.mapper.TodolistMapper;
import com.catchme.todolist.todolist.repository.TodolistRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<TodolistDto> findAllTodos(){
        List<TodolistEntity> todolistEntityList = todolistRepository.findByIsDeletedFalse();
        return todolistMapper.toDto(todolistEntityList);
    }

    @Transactional(readOnly = true)
    public TodolistDto findTodolistById(Long id){
        TodolistEntity todolistEntity = todolistRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("해당 Todolist를 찾을 수 없습니다."));
        return todolistMapper.toDto(todolistEntity);
    }

    @Transactional
    public TodolistDto updateTodolist(Long id, TodolistDto todolistDto){
        TodolistEntity todolistEntity = todolistRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));
        todolistEntity.update(todolistDto.getTitle(), todolistDto.getDescription(), todolistDto.getStartDate(), todolistDto.getDueDate());
        todolistRepository.save(todolistEntity);
        return todolistMapper.toDto(todolistEntity);
    }

    @Transactional
    public void deleteTodolist(Long id){
        TodolistEntity todolistEntity = todolistRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("할일이 존재하지 않습니다."));
        todolistEntity.delete();
        todolistRepository.save(todolistEntity);
    }
}

