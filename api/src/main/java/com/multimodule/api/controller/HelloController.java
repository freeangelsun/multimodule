package com.multimodule.api.controller;

import com.multimodule.api.dto.MemberDto;
import com.multimodule.api.dto.MemberResponse;
import com.multimodule.common.dao.member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        Member member = new Member();
        return "hello api";
    }

    @PostMapping(value = "/{version}/test/member")
    public ResponseEntity<MemberDto.MemberResponse> getMember(@PathVariable("version") String version,
                                                    @Validated @RequestBody MemberDto.MemberRequest request,
                                                    BindingResult bindingResult,
                                                    @RequestHeader  Map<String, String> requestHeader){

        MemberDto.MemberResponse  result = new MemberDto.MemberResponse("정찬길", "10");
        result.setReqId(request.getReqId());
        result.setRspCode("00");
        result.setRspMessage("성공메시지");
        return ResponseEntity.ok().headers(request.getReqIdHeader()).body(result);
    }
}
