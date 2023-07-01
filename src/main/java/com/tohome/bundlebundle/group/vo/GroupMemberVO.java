package com.tohome.bundlebundle.group.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class GroupMemberVO {
    private Integer groupId;
    private Integer memberId;
    private String groupNickname;
}
