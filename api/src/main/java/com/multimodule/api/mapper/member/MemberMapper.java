package com.multimodule.api.mapper.member;

import com.multimodule.api.dto.member.MemberDto;
import com.multimodule.api.vo.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "memberMapper") @Primary
public interface MemberMapper{
    List<Member> getMemberList();
    Member getMember(String id);
}

