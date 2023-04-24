package com.multimodule.api.controller;

import com.multimodule.common.dao.member.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        Member member = new Member();
        return "hello api";
    }
}
