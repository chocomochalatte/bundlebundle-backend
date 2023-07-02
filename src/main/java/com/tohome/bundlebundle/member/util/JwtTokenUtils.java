package com.tohome.bundlebundle.member.util;

import com.tohome.bundlebundle.member.vo.MemberVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); //HS256알고리즘으로 키 생성
    private static final long EXPIRATION_TIME = 86400000; // 24시간 (단위: 밀리초)

    // JWT 토큰 생성
    public String generateJwtToken(MemberVO user) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("address", user.getAddress());
        // 필요한 경우 추가적인 클레임 설정

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT 토큰 유효성 검사
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 사용자 정보 추출
    public MemberVO getUserFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        String email = (String) claims.get("email");
        String address = (String) claims.get("address");
        // 필요한 경우 추가적인 클레임 추출

        MemberVO user = new MemberVO();
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        // 필요한 경우 추가적인 사용자 정보 설정

        return user;
    }
}
