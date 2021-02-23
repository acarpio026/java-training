package com.bootcamp.controller;

import com.bootcamp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("security")
public class SecurityController {

    private UserService service;

    @Autowired
    public SecurityController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("usercount", service.getUserCount());
        return "security/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "security/login";
    }

}
