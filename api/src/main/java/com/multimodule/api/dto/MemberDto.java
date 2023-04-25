package com.multimodule.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;


public class MemberDto {
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class MemberRequest extends Request {
        @NotEmpty(message = "member_id는 필수 입니다.")
        @JsonProperty("member_id")
        private String memberId;

    }


    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class MemberResponse extends Response {

        @JsonProperty("member_name")
        public String name;

        @JsonProperty("member_age")
        public String age;

        public MemberResponse (String name, String age)
        {
            this.name = name;
            this.age = age;
        }
    }
}
