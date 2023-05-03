package com.multimodule.api.listener;

import com.multimodule.api.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@Component
public class MemberEventListener {
    @EventListener
    @Order(1)   // 숫자가 작을수록 우선 순위가 높다
    public void eventLog(MemberDto.MemberListResponse event) {
        log.info(String.format("회원 목록조회 Event Order(1) [목록갯수 : %s]", event.getSize()));
    }

    @EventListener
    @Order(2)   // 숫자가 작을수록 우선 순위가 높다
    public void eventLog2(MemberDto.MemberListResponse event) {
        log.info(String.format("회원 목록조회 Event Order(2) [목록갯수 : %s]", event.getSize()));
    }


    // 트랜잭션 처리로직이 없어서 메소드가 비활성화 되나
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Order(3)   // 숫자가 작을수록 우선 순위가 높다
    public void eventLog3(MemberDto.MemberListResponse event) {
        log.info(String.format("회원 목록조회 Event Order(3) [목록갯수 : %s]", event.getSize()));
    }
}
