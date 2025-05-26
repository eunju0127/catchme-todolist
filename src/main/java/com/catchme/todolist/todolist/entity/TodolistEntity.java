package com.catchme.todolist.todolist.entity;

import com.catchme.todolist.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Todolist")
public class TodolistEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @Comment("할일 번호")
    private Long id;

    @Column(name = "title", nullable = false)
    @Comment("할일 제목")
    private String title;

    @Column(name = "description")
    @Comment("할일 상세 내용")
    private String description;

    @Column(name = "start_date")
    @Comment("시작일")
    private LocalDate startDate;

    @Column(name = "due_date")
    @Comment("마감일")
    private LocalDate dueDate;

    @Setter
    @Column(name = "is_completed", nullable = false)
    @Comment("완료 여부")
    private Boolean isCompleted = false;

    @Setter
    @Column(name = "is_pinned", nullable = false)
    @Comment("즐겨찾기 여부")
    private Boolean isPinned = false;

    @Column(name = "is_deleted", nullable = false)
    @Comment("삭제 여부")
    private Boolean isDeleted;

    public void update(String title, String description, LocalDate startDate, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
