package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.group.vo.GroupMemberVO;

public interface MemberService {

    boolean hasGroupCart(Integer memberId);

    GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO);


}
