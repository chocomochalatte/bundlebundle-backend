package com.tohome.bundlebundle.group.service;

import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;

public interface GroupService {

    GroupVO createGroup(Integer memberId, GroupNicknameVO groupId);

}
