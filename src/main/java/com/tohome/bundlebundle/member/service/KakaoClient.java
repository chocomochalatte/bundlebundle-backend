package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.member.vo.KakaoUserResponse;
import com.tohome.bundlebundle.member.vo.MemberVO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoClient {

    private final RestTemplate restTemplate;

    public KakaoClient() {
        this.restTemplate = new RestTemplate();
    }

    public MemberVO getUser(String accessToken) throws NullPointerException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                KakaoUserResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            KakaoUserResponse kakaoUserResponse = response.getBody();
            if (kakaoUserResponse != null) {
                return kakaoUserResponse.toMemberVO();
            }
        }

        throw new NullPointerException("Failed to retrieve user information from Kakao.");
    }
}
