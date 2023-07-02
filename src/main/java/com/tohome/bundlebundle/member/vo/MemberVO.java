package com.tohome.bundlebundle.member.vo;

<<<<<<< HEAD
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
=======

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
>>>>>>> 6ce2b68b3ac099e5d167d8276da0dc6084a014b1
