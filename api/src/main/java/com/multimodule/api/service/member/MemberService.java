package com.multimodule.api.service.member;

import com.multimodule.api.dto.member.MemberDto;
import com.multimodule.api.mapper.member.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public MemberDto.MemberDBResponse getMemberList(){
        MemberDto.MemberDBResponse result = new MemberDto.MemberDBResponse(memberMapper.getListMember());
        return result;
    }

}
