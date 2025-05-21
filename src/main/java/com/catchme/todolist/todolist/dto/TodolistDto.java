package com.catchme.todolist.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TodolistDto {

    private Long id;

    @NotBlank(message = "할일 제목은 필수로 입력해야 합니다.")
    @Size(max = 50, message = "할일 제목은 최대 50자까지 입력할 수 있습니다.")
    private String title;

    @Size(max = 200, message = "할일 설명은 최대 200자까지 입력할 수 있습니다.")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private Boolean isCompleted;

    @NotNull
    private Boolean isPinned;

    @NotNull
    private Boolean isDeleted;
}
