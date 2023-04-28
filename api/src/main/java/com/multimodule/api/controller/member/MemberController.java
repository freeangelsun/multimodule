package com.multimodule.api.controller.member;

import com.multimodule.api.annotation.GlobId;
import com.multimodule.api.dto.member.MemberDto;
import com.multimodule.api.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/{version}/memberList")
    public ResponseEntity<MemberDto.MemberResponse> getMember(@PathVariable("version") String version,
                                                              @Validated @RequestBody MemberDto.MemberRequest request,
                                                              BindingResult bindingResult,
                                                              @RequestHeader  Map<String, String> requestHeader){

        MemberDto.MemberResponse  result = new MemberDto.MemberResponse("정찬길", "10");
        result.setReqId(request.getReqId());

        log.debug("debug : #####################################################################################################");

        if (bindingResult.hasErrors()) {
            result.setRspCode("01");
            result.setRspMessage("에러메시지");
        }
        else {
            result.setRspCode("00");
            result.setRspMessage("성공메시지");
        }
        return ResponseEntity.ok().headers(request.getReqIdHeader()).body(result);
    }


    @PostMapping(value = "/{version}/DB/memberList")
    public ResponseEntity<MemberDto.MemberDBResponse> getDBMember(@GlobId String globId,
                                                              @PathVariable("version") String version,
                                                              @Validated @RequestBody MemberDto.MemberRequest request,
                                                              BindingResult bindingResult,
                                                              @RequestHeader  Map<String, String> requestHeader){


        MemberDto.MemberDBResponse result = memberService.getMemberList();
        result.setReqId(request.getReqId());
        log.debug("debug :"+globId+" : #####################################################################################################");

        if (bindingResult.hasErrors()) {
            result.setRspCode("01");
            result.setRspMessage("에러메시지");
        }
        else {
            result.setRspCode("00");
            result.setRspMessage("성공메시지");
        }
        return ResponseEntity.ok().headers(request.getReqIdHeader()).body(result);
    }
}
