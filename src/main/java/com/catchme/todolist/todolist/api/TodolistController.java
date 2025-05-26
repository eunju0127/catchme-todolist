package com.catchme.todolist.todolist.api;

import com.catchme.todolist.todolist.dto.TodolistDto;
import com.catchme.todolist.todolist.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
@Tag(name = "Todolist", description = "Todolist API")
public class TodolistController {

    private final TodolistService todolistService;

    @PostMapping
    @Operation(summary = "할일 생성", description = "새로운 할일을 생성합니다.")
    public ResponseEntity<TodolistDto> createTodolist(
            @RequestBody @Valid TodolistDto todolistDto) {
        TodolistDto savedTodolist = todolistService.createTodolist(todolistDto);
        return ResponseEntity.ok(savedTodolist);
    }

    @GetMapping
    @Operation(summary = "할일 전체 조회", description = "모든 할일을 조회합니다.")
    public ResponseEntity<List<TodolistDto>> getAllTodos(){
        List<TodolistDto> todolistDtoList = todolistService.findAllTodos();
        return todolistDtoList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(todolistDtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "할일 개별 조회", description = "하나의 할일을 조회합니다.")
    public ResponseEntity<TodolistDto> getTodolistById(@PathVariable Long id) {
        TodolistDto todolistDto = todolistService.findTodolistById(id);
        return ResponseEntity.ok(todolistDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "할일 수정", description = "기존 할일을 수정합니다.")
    public ResponseEntity<TodolistDto> updateTodolist(@PathVariable("id") Long Id,
            @RequestBody @Valid TodolistDto todolistDto){
        TodolistDto updatedTodolist = todolistService.updateTodolist(Id, todolistDto);
        return ResponseEntity.ok(updatedTodolist);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "할일 삭제", description = "할일을 삭제합니다.")
    public ResponseEntity<Map<String, String>> deleteTodolist(@PathVariable("id") Long id){
        todolistService.deleteTodolist(id);
        Map<String, String> response = Map.of("message", "할일이 성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/completion")
    @Operation(summary = "할일 완료 체크", description = "할일을 완료로 변경합니다.")
    public ResponseEntity<Map<String, String>> completeTodolist(@PathVariable("id") Long id) {
        todolistService.completeTodolist(id);
        return ResponseEntity.ok(Map.of("message", "할일이 완료되었습니다."));
    }

    @PatchMapping("/{id}/pin")
    @Operation(summary = "할일 즐겨찾기", description = "할일을 즐겨찾기합니다.")
    public ResponseEntity<Map<String, String>> pinTodolist(@PathVariable("id") Long id) {
        todolistService.pinTodolist(id);
        return ResponseEntity.ok(Map.of("message", "해당 할일이 즐겨찾기 되었습니다."));
    }
}
