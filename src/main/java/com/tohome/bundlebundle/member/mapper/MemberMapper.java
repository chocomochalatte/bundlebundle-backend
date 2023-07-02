package com.tohome.bundlebundle.member.mapper;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;
import com.tohome.bundlebundle.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberVO> findMemberById(Integer memberId);

    Integer findGroupIdById(Integer memberId);

    Integer updateGroup(GroupMemberVO groupMemberVO);

    Integer updateGroupNickname(MemberGroupNicknameVO memberGroupNicknameVO);

    Integer deleteGroupIdById(Integer memberId);

    List<MemberVO> findAllByGroupId(Integer groupId);
}
