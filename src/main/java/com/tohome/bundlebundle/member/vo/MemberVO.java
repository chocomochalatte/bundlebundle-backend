package com.tohome.bundlebundle.member.vo;


import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class MemberVO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private Timestamp created_at;
    private String groupNickname;
    private Integer groupId;
}
