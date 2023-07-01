package com.tohome.bundlebundle.group.service;

import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;

public interface GroupService {

    GroupVO createGroupCart(Integer memberId, GroupNicknameVO groupNicknameVO);

    Integer findOwningGroupId(Integer memberId);

    Integer deleteOwningGroup(Integer memberId);
}
