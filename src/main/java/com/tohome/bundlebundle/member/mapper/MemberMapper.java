package com.tohome.bundlebundle.member.mapper;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Integer updateGroupNickname(GroupMemberVO groupMemberVO);
}
