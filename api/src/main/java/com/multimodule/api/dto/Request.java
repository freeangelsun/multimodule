package com.multimodule.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @JsonIgnore
    private  String reqId;

    @JsonIgnore
    public HttpHeaders getReqIdHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-req-id", reqId);
        return httpHeaders;
    }
}
