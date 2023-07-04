package com.tohome.bundlebundle.member.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MemberVO {
    private Integer id;
    @JsonProperty("username")
    private String username;
    private String password;
    @JsonProperty("account_email")
    private String email;
    private String address;
    private String phoneNumber;
    private Timestamp created_at;
    private String groupNickname;
    private Integer groupId;
    @JsonProperty("profile_image_url")
    private String userProfileImg;
}
