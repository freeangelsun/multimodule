package com.multimodule.admin.member;


import com.multimodule.admin.vo.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "memberMapper") @Primary
public interface MemberMapper {
    List<Member> getListMember();
}
