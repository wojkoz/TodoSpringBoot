package com.example.verifinator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/todo")
public class TodoController {

    @GetMapping()
    public String getHello(){
        return "getHello";
    }
}
