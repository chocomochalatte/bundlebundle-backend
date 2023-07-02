package com.tohome.bundlebundle.group.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberVO {
    private Integer groupId;
    private Integer memberId;
    private String groupNickname;

    public GroupMemberVO(GroupVO groupVO, GroupNicknameVO groupNicknameVO) {
        this.groupId = groupVO.getId();
        this.memberId = groupVO.getGroupOwnerId();
        this.groupNickname = groupNicknameVO.getGroupNickname();
    }

    public void addMemberId(Integer memberId) {
        this.memberId = memberId;
    }

}
