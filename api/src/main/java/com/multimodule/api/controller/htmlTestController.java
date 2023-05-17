package com.multimodule.api.controller;

import com.multimodule.api.service.member.AsyncTestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class htmlTestController {
    /**
     *  application.yml 설정추가
     *   thymeleaf:
     *     prefix: classpath:/static/
     *         <p> application.yml 설정추가 thymeleaf: prefix: classpath:/static/</p> </ br>
     *
     *  build.gradle 설정 추가
     *  implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
     */
    @GetMapping("/index")
    public String testAsync() {
        return "testIndex.html";
    }

    @ResponseBody
    @GetMapping("/testData")
    public String index() {
       String data = "ResponseBody 반환";
        return data;
    }
}
