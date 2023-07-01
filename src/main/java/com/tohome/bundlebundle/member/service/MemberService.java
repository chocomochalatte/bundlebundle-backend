package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;

public interface MemberService {

    MemberGroupNicknameVO updateGroupNickname(Integer memberId, GroupNicknameVO groupNicknameVO);

    Integer findPresentGroupId(Integer memberId);

    GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO);

    void getOutOfGroup(Integer memberId);
}
