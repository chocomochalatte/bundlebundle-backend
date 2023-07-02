package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;
import com.tohome.bundlebundle.member.vo.MemberVO;
import org.springframework.lang.NonNull;

public interface MemberService {

    MemberGroupNicknameVO updateGroupNickname(Integer memberId, GroupNicknameVO groupNicknameVO);

    Integer findPresentGroupId(Integer memberId);

    GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO);

    Integer getOutOfGroup(Integer memberId);

    MemberVO findUserByEmail(String email);

    Boolean insertUser(MemberVO user) throws Exception;

    MemberVO updateUser(MemberVO user) throws Exception;

    /*    String findUserByEmail (String email);*/
}
