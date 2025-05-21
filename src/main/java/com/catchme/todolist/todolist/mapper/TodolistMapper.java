package com.catchme.todolist.todolist.mapper;

import com.catchme.todolist.todolist.dto.TodolistDto;
import com.catchme.todolist.todolist.entity.TodolistEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodolistMapper {

    TodolistDto toDto(TodolistEntity todolistEntity);
    TodolistEntity toEntity(TodolistDto todolistDto);
    List<TodolistDto> toDto(List<TodolistEntity> todolistEntityList);
    List<TodolistEntity> toEntity(List<TodolistDto> todolistDtoList);
}
