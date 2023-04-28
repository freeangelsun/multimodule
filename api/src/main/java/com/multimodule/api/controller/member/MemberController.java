package com.multimodule.api.controller.member;

import com.multimodule.api.annotation.GlobId;
import com.multimodule.api.dto.member.MemberDto;
import com.multimodule.api.service.member.MemberCacheService;
import com.multimodule.api.service.member.MemberService;
import com.multimodule.api.vo.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCacheService memberCacheService;


    @PostMapping(value = "/{version}/memberList")
    public ResponseEntity<MemberDto.MemberListResponse> getMemberList(@GlobId String globId,
                                                                    @PathVariable("version") String version,
                                                                    @Validated @RequestBody MemberDto.MemberRequest request,
                                                                    BindingResult bindingResult,
                                                                    @RequestHeader  Map<String, String> requestHeader){


        MemberDto.MemberListResponse result = memberService.getMemberList();
        result.setReqId(request.getReqId());
        log.debug("globId : {}", globId);

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



    /**
     * EHCACHE 테스트
     */
    @PostMapping(value = "/{version}/cache/member")
    public ResponseEntity<Member> getCacheMember(@GlobId String globId,
                            @PathVariable("version") String version,
                            @Validated @RequestBody MemberDto.MemberRequest request,
                            BindingResult bindingResult,
                            @RequestHeader  Map<String, String> requestHeader){


        log.debug("globId : {}, id : {}", globId, request.getId());

        Member result = memberCacheService.getMember(request.getId());

        return ResponseEntity.ok(result);
    }

    /**
     * EHCACHE 테스트
     */
    @PostMapping(value = "/{version}/cache/deleteMember")
    public ResponseEntity getCacheDeleteMember(@GlobId String globId,
                                                 @PathVariable("version") String version,
                                                 @Validated @RequestBody MemberDto.MemberRequest request,
                                                 BindingResult bindingResult,
                                                 @RequestHeader  Map<String, String> requestHeader){


        log.debug("globId : {}, id : {}", globId, request.getId());

        memberCacheService.updateMemberName(request.getId());

        return ResponseEntity.ok().body("Ok");
    }



    // 캐시 전체 삭제
    @PostMapping(value = "/{version}/cache/remove/memberList")
    public ResponseEntity getCacheRemoveMemberList(@GlobId String globId,
                                                               @PathVariable("version") String version,
                                                               @Validated @RequestBody MemberDto.MemberRequest request,
                                                               BindingResult bindingResult,
                                                               @RequestHeader  Map<String, String> requestHeader){

        memberCacheService.deleteMemberList();
        return ResponseEntity.ok().body("Ok");
    }

    @PostMapping(value = "/{version}/cache/memberList")
    public ResponseEntity<List<Member>> getCacheMemberList(@GlobId String globId,
                                                           @PathVariable("version") String version,
                                                           @Validated @RequestBody MemberDto.MemberRequest request,
                                                           BindingResult bindingResult,
                                                           @RequestHeader  Map<String, String> requestHeader){


        log.debug("globId : {}", globId);

        List result = memberCacheService.getMemberList();

        return ResponseEntity.ok(result);
    }
}
