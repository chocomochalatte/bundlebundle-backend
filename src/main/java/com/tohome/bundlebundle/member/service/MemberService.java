package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.group.vo.GroupIdVO;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;

public interface MemberService {

    Integer findPresentGroupId(Integer memberId);

    GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO);


}
