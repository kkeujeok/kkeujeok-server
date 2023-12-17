package com.kkeujeok.domain.user.Utils;

import com.kkeujeok.domain.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    // 설정파일로 빼서 환경변수로 사용하는 것을 권장
    private final String SECRET = "secretyurayuraryuararyuarayraururayruaryauryaurayruryaurayruryruaryaura";

    /**
     * 토큰 생성
     */
    public String generateToken(User userDetails) {
        Claims claims = Jwts.claims();
        claims.put("userId", userDetails.getId());
        return createToken(claims);
        // userId을 subject로 해서 token 생성
    }

    private String createToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 1시간
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * 토큰 유효여부 확인
     */
    public Boolean isValidToken(String token, User userDetails) {
        log.info("isValidToken token = {}", token);
        Long userId = getUserIdFromToken(token);
        return (userId.equals(userDetails.getId()) && !isTokenExpired(token));
    }

    /**
     * 토큰의 Claim 디코딩
     */
    private Claims getAllClaims(String token) {
        log.info("getAllClaims token = {}", token);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Claim 에서 userId 가져오기
     */
    public Long getUserIdFromToken(String token) {
        Long userId = Long.parseLong(String.valueOf(getAllClaims(token).get("userId")));
        log.info("getUserIdFormToken subject = {}", userId);
        return userId;
    }

    /**
     * 토큰 만료기한 가져오기
     */
    public Date getExpirationDate(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }

    /**
     * 토큰이 만료되었는지
     */
    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
}