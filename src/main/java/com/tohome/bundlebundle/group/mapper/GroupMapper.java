package com.tohome.bundlebundle.group.mapper;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMapper {

    Integer createGroup(GroupVO groupNicknameVO);

    Integer findGroupIdByGroupOwnerId(Integer memberId);

    Integer deleteGroupById(Integer groupId);

	void insertToken(GroupMemberVO groupMemberVO);
}
