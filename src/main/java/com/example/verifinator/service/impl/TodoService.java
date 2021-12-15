package com.example.verifinator.service.impl;

import com.example.verifinator.domain.dto.TodoDto;
import com.example.verifinator.domain.entity.Todo;
import com.example.verifinator.service.ITodoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    private List<Todo> _db = new ArrayList<>();

    @Override
    public Optional<TodoDto> getById(Long id) {
        Optional<Todo> todo = _db.stream()
                .filter(item -> Objects.equals(item.getId(), id)).findFirst();
        if(todo.isEmpty()){
            return Optional.empty();
        }

        Todo t = todo.get();
        return Optional.of(new TodoDto(t.getId(), t.getTitle(), t.getDescription()));
    }

    @Override
    public Iterable<TodoDto> getAll() {

        return _db.stream().map(todo -> new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription())
                )
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto create(TodoDto dto) {
        Todo todo = new Todo((long) _db.size() +1, dto.getTitle(), dto.getDescription());
        _db.add(todo);
        dto.setId(todo.getId());
        return dto;
    }

    @Override
    public TodoDto update(TodoDto dto) {
        Optional<Todo> todo = _db.stream()
                .filter(item -> Objects.equals(item.getId(), dto.getId())).findFirst();

        if(todo.isEmpty()){
            return null;
        }
        Todo t = todo.get();
        int index = _db.indexOf(t);

        t.setDescription(dto.getDescription());
        t.setTitle(dto.getTitle());

        _db.set(index, t);

        return new TodoDto(t.getId(), t.getTitle(), t.getDescription());
    }
}
