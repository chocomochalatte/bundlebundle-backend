package com.tohome.bundlebundle.member.util;

import com.tohome.bundlebundle.member.vo.MemberVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private static final byte[] SECRET_KEY = DatatypeConverter.parseHexBinary("236979CB6F1AD6B6A6184A31E6BE37DB3818CC36871E26235DD67DCFE4041492");
    private static final long EXPIRATION_TIME = 86400000; // 24시간 (단위: 밀리초)

    // JWT 토큰 생성
    public String generateJwtToken(MemberVO user) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        Claims claims = Jwts.claims().setSubject("JWTToken");
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("address", user.getAddress());
        claims.put("id", user.getId());
        claims.put("groupId", user.getGroupId());
        claims.put("userProfileImg", user.getUserProfileImg());
        System.out.println("claimn result: " + claims);
        // 필요한 경우 추가적인 클레임 설정Gou

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
        String username = (String)claims.get("username");
        String email = (String) claims.get("email");
        Integer id = (Integer) claims.get("id");
        Integer groupId = (Integer) claims.get("groupId");
        String userProfileImg = (String) claims.get("userProfileImg");
        String address = (String) claims.get("address");
        // 필요한 경우 추가적인 클레임 추출

        MemberVO user = new MemberVO();
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        user.setId(id);
        user.setGroupId(groupId);
        user.setUserProfileImg(userProfileImg);

        // 필요한 경우 추가적인 사용자 정보 설정

        return user;
    }


}
