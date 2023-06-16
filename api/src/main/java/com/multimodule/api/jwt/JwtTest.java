package com.multimodule.api.jwt;

import io.jsonwebtoken.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Date;

// main 함수가 있는 클래스
public class JwtTest {
    // 암호화하기 위한 키
    private static String SECRET_KEY = "secret1234";
    // JWT 만료 시간 1시간
    private static long tokenValidMilisecond = 1000L * 60 * 60;

    // 실행 함수
    public static void main(String[] args) {
        // 설정한 암호화키를 Base64로 암호화한다.
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());

        // User 인스턴스 생성
        var user = new User();
        // Id 설정
        user.setId("nowonbun");
        // UserName 설정
        user.setUserName("hello world");


        // jwtTest 인스턴스 생성
        var jwtTest = new JwtTest();
        // User 인스턴스를 직렬화한다.
        var code = jwtTest.convertSerializable(user);

        // 직렬화된 코드에 1 코드를 추가한다.
        code = "1" + code;
        // JWT 토큰 생성
        var token = jwtTest.createToken(code);
        // 콘솔 출력
        System.out.println("token : "+ token);
        System.out.println("======================================================================");


        // JWT 토큰 복호화
        var claims = jwtTest.getClaims(token);
        // JWT 토큰 검증
        if (claims != null && jwtTest.validateToken(claims)) {
            // id를 취득한다.
            var id = jwtTest.getKey(claims);
            // 콘솔 출력
            System.out.println("id : "+id);
            System.out.println("======================================================================");


            // 1코드를 제거
            id = id.substring(1);
            // 역직렬화
            user = jwtTest.convertData(id);
            // 콘솔 출력
            user.print();
        } else {
            // 토큰이 정합성이 맞지 않으면
            System.out.println("error");
        }

    }
    // 직렬화 함수
    public String convertSerializable(User user) {
        try (var baos = new ByteArrayOutputStream()) {
            try (var oos = new ObjectOutputStream(baos)) {
                oos.writeObject(user);
                // 직렬화 코드
                var data = baos.toByteArray();
                // 직렬화된 것은 Base64로 암호화
                return Base64.getEncoder().encodeToString(data);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
    // 역직렬화 함수
    public User convertData(String code) {
        // Base64 복호화
        var data = Base64.getDecoder().decode(code);
        // 역직렬화
        try (var bais = new ByteArrayInputStream(data)) {
            try (var ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                // User 인스턴스로 캐스팅
                return (User) objectMember;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    // 토큰 생성 함수
    public String createToken(String key) {
        // Claims을 생성
        var claims = Jwts.claims().setId(key);
        // 현재 시간
        Date now = new Date();
        // JWT 토큰을 만드는데, Payload 정보와 생성시간, 만료시간, 알고리즘 종료와 암호화 키를 넣어 암호화 함.
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // String으로 된 코드를 복호화한다.
    public Jws<Claims> getClaims(String jwt) {
        try {
            // 암호화 키로 복호화한다.
            // 즉 암호화 키가 다르면 에러가 발생한다.
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt);
        } catch (SignatureException e) {
            return null;
        }
    }

    // 토큰 검증 함수
    public boolean validateToken(Jws<Claims> claims) {
        // 토큰 만료 시간이 현재 시간을 지났는지 검증
        return !claims.getBody()
                .getExpiration()
                .before(new Date());
    }

    // 토큰을 통해 Payload의 ID를 취득
    public String getKey(Jws<Claims> claims) {
        // Id 취득
        return claims.getBody().getId();
    }

    // 토큰을 통해 Payload의 데이터를 취득
    public Object getClaims(Jws<Claims> claims, String key) {
        // 데이터 취득
        return claims.getBody().get(key);
    }
}