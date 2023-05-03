package com.multimodule.api.service.member;

import com.multimodule.api.dto.member.MemberDto;
import com.multimodule.api.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    final private ApplicationEventPublisher applicationEventPublisher;

    public MemberDto.MemberListResponse getMemberList(){

        MemberDto.MemberListResponse result = new MemberDto.MemberListResponse(memberMapper.getMemberList());

        // event 발생
        applicationEventPublisher.publishEvent(result);
        
        return result;
    }
}
