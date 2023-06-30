package com.tohome.bundlebundle.member.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    @JsonProperty("username")
    private String username;
    private String password;
    @JsonProperty("account_email")
    private String email;
    private String address;
    private String phoneNumber;
    private String groupNickname;
    private Integer groupId;
}