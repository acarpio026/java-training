package com.bootcamp.controller;

import com.bootcamp.model.Todo;
import com.bootcamp.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/todo")
public class TodoApi {

    private TodoService service;

    @Autowired
    public TodoApi(TodoService service) {
        this.service = service;
    }

    @PatchMapping(value = "/")
    public ResponseEntity<String> update(@RequestBody Todo data) {
        return service.updateTodo(data);
    }


    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<String> delete(@PathVariable  Long todoId) {
        return service.deleteTodo(todoId);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> add(@RequestBody Todo data) {
        return service.addTodo(data);
    }

}
