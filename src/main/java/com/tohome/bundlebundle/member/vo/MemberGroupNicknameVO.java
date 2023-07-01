package com.tohome.bundlebundle.member.vo;

import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberGroupNicknameVO {
    private Integer memberId;
    private String groupNickname;

    public MemberGroupNicknameVO(Integer memberId, GroupNicknameVO groupNicknameVO) {
        this.memberId = memberId;
        this.groupNickname = groupNicknameVO.getGroupNickname();
    }
}
