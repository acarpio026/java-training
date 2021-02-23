package com.bootcamp.controller;

import com.bootcamp.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:todo";
    }

    @GetMapping("/todo")
    public String index() {
        return "todo/index";
    }

    @GetMapping({"/todo/list" })
    public String getList(@RequestParam("includeDone") Boolean includeDone, Model model) {
        model.addAttribute("todos", todoService.getTodoList(includeDone)) ;
        return "todo/_list";
    }

}
