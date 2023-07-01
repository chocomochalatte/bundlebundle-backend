package com.tohome.bundlebundle.member.mapper;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<Integer> hasGroupCart(Integer memberId);

    Integer updateGroupNickname(GroupMemberVO groupMemberVO);

}
