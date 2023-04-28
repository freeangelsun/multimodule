package com.multimodule.api.service.member;

import com.multimodule.api.mapper.member.MemberMapper;
import com.multimodule.api.vo.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCacheService {
    private final MemberMapper memberMapper;

    /**
     * @Cacheable : 캐시가 있으면 캐시의 정보를 가져오고 없으면 등록합니다..
     * @CachePut : 무조건 캐시에 저장합니다. 캐시를 갱신하기 위해 실행을 강제합니다.
     * @CacheEvict : 캐시를 삭제합니다.
     */

    // @Cacheable의 인자로는 cacheNames(혹은 value), key, condition을 지정할 수 있다. 2.x 버전에서는 value라고 표현했으나, 3.x버전에서는 cacheNames로 표현한다. 웬만하면 3버전의 이름을 사용하자.
    @Cacheable(
            cacheNames = "memberCacheInfo",
            key = "#id"
    )
    public Member getMember(String id){
        log.debug("id {}", id);
        Member member = memberMapper.getMember(id);
        return member;
    }

    // 캐시를 삭제합니다.
    @CacheEvict(
            cacheNames = "memberCacheInfo",
            key = "#id"
    )
    @Transactional
    public void updateMemberName(String id){
       // memberMapper.getMember(id);            // 삭제 하려면 주석처리
    }


    /**
     * 키 없는 목록 처리
     * ehcache.xml 에 키 타이을 string으로 해서 목록데이터를 통으로 넣지는 못한다.
      */

    @Cacheable(cacheNames = "memberCacheInfo", key="#List.Member.id")
    @Transactional(readOnly = true)
    public List<Member> getMemberList(){
        List result = memberMapper.getMemberList();
        return result;
    }

    // 캐시를 삭제합니다.
    @CacheEvict(cacheNames = "memberCacheInfo", allEntries = true)
    public void deleteMemberList(){

    }
}
