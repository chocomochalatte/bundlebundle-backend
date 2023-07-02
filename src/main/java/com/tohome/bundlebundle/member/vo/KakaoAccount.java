package com.tohome.bundlebundle.member.vo;

import lombok.Data;

@Data
public class KakaoAccount {
    String email;
    Profile profile;

    @Data
    public class Profile{
        String nickname;
        //미리보기 이미지 : 만약 더 높은 해상도가 필요한 경우 profile_image 사용
        String profile_image_url;
    }

}
