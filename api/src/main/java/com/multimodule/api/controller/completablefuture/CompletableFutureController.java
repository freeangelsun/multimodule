package com.multimodule.api.controller.completablefuture;

import com.multimodule.api.service.member.CompletableCutureService;
import com.multimodule.common.dao.member.Member;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class CompletableFutureController {
    private CompletableCutureService completableCutureService;


    @GetMapping("/completablefuture")
    public String completablefuture() throws Exception{
        //log.debug("completableCutureService.completablefuture1()");
        //completableCutureService.completablefuture1();

        //log.debug("completableCutureService.completablefuture2()");
        //completableCutureService.completablefuture2();

       //log.debug("completableCutureService.completablefuture3()");
        //completableCutureService.completablefuture3();

        log.debug("completableCutureService.completablefuture4()");
        completableCutureService.completablefuture4();

        //log.debug("completableCutureService.completablefuture5()");
        //completableCutureService.completablefuture5();


        return "hello completablefuture";
    }

}
