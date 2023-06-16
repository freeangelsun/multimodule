package com.multimodule.api.jwt;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    // 맴버 변수
    private String id;
    private String userName;
    // setter
    public void setId(String id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    // 맴버 변수 출력 함수
    public void print() {
        // 콘솔 출력
        System.out.println("id = " + this.id + " userName = " + this.userName);
    }
}