package com.multimodule.api.dto.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.multimodule.api.dto.Request;
import com.multimodule.api.dto.Response;
import com.multimodule.api.vo.member.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


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

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    public static class MemberDBResponse extends Response {

        private int size;
        private List<Object> memberList;
        public MemberDBResponse(List<Member> members)
        {
            this.size = members.size();
            this.memberList = new ArrayList();

            for( Member member : members){
                memberList.add(MemberInfo.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .age(member.getAge())
                        .phone(member.getPhone())
                        .email(member.getEmail())
                        .address(member.getAddress())
                        .date(member.getDate())
                        .build()
                );
            }
        }
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MemberInfo{
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("age")
        private String age;

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("address")
        private String address;

        @JsonProperty("date")
        private String date;
    }
}
