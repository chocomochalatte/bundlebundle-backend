package com.tohome.bundlebundle.member.vo;

import lombok.Data;

@Data
public class KakaoUserResponse {

    KakaoAccount kakao_account;

    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .email(this.kakao_account.email)
                .username(this.kakao_account.profile.nickname)
                .userProfileImg(this.kakao_account.profile.profile_image_url)
                .build();
    }
}