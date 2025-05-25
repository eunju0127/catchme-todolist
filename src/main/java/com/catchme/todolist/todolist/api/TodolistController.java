package com.catchme.todolist.todolist.api;

import com.catchme.todolist.todolist.dto.TodolistDto;
import com.catchme.todolist.todolist.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodolistController {

    private final TodolistService todolistService;

    @PostMapping
    @Operation(summary = "할일 생성")
    public ResponseEntity<TodolistDto> createTodolist(
            @RequestBody @Valid TodolistDto todolistDto) {
        TodolistDto savedTodolist = todolistService.createTodolist(todolistDto);
        return ResponseEntity.ok(savedTodolist);
    }

    @GetMapping
    @Operation(summary = "할일 전체 조회")
    public ResponseEntity<List<TodolistDto>> getAllTodos(){
        List<TodolistDto> todolistDtoList = todolistService.findAllTodos();
        return todolistDtoList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(todolistDtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "할일 개별 조회")
    public ResponseEntity<TodolistDto> getTodolistById(@PathVariable Long id) {
        TodolistDto todolistDto = todolistService.findTodolistById(id);
        return ResponseEntity.ok(todolistDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "할일 수정")
    public ResponseEntity<TodolistDto> updateTodolist(@PathVariable("id") Long Id,
            @RequestBody @Valid TodolistDto todolistDto){
        TodolistDto updatedTodolist = todolistService.updateTodolist(Id, todolistDto);
        return ResponseEntity.ok(updatedTodolist);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "할일 삭제")
    public ResponseEntity<Map<String, String>> deleteTodolist(@PathVariable("id") Long id){
        todolistService.deleteTodolist(id);
        Map<String, String> response = Map.of("message", "할 일이 성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }
}
