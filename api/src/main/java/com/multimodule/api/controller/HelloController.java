package com.multimodule.api.controller;

import com.multimodule.api.annotation.GlobId;
import com.multimodule.api.dto.member.MemberDto;
import com.multimodule.common.dao.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        Member member = new Member();
        return "hello api";
    }
}
