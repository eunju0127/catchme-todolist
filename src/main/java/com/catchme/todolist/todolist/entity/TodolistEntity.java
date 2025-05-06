package com.catchme.todolist.todolist.entity;

import com.catchme.todolist.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Todolist")
public class TodolistEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;
}
