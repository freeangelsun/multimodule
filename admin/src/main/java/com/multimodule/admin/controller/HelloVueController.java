package com.multimodule.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloVueController {
    @GetMapping("/vue")
    public String vue() {

        return "/index.html";
    }
}
