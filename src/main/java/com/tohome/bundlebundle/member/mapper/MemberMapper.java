package com.tohome.bundlebundle.member.mapper;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberVO> findMemberById(Integer memberId);

    Optional<Integer> findGroupIdById(Integer memberId);

    Integer updateMemberGroup(GroupMemberVO groupMemberVO);
}
