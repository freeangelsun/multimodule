package com.multimodule.api.controller;

import com.multimodule.api.service.member.AsyncTestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@AllArgsConstructor
@Controller
public class AsyncTestController {
    private AsyncTestService asyncTestService;

    @GetMapping("async/test")
    public String testAsync() {
        log.info("TEST ASYNC");
        for(int i=0; i<20; i++) {
            asyncTestService.asyncMethod(i);
        }
        return "test.html";
    }
}
