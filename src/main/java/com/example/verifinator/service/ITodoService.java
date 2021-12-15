package com.example.verifinator.service;

import com.example.verifinator.domain.dto.TodoDto;

import java.util.Optional;

public interface ITodoService {
    Optional<TodoDto> getById(Long id);
    Iterable<TodoDto> getAll();
    TodoDto create(TodoDto dto);
    TodoDto update(TodoDto dto);
}
