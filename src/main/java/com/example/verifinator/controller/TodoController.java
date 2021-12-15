package com.example.verifinator.controller;

import com.example.verifinator.domain.dto.TodoDto;
import com.example.verifinator.service.ITodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController()
@RequestMapping(value = "/api/todo")
public class TodoController {

    private final ITodoService _todoService;

    public TodoController(ITodoService todoService) {
        _todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<TodoDto>> getAll(){
        return new ResponseEntity<>(_todoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> getById(@PathVariable Long id){
        Optional<TodoDto> dto = _todoService.getById(id);

        return dto.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(dto.get(), HttpStatus.OK);
    }
}
