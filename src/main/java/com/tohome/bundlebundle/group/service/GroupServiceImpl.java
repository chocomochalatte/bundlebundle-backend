package com.tohome.bundlebundle.group.service;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.group.mapper.GroupMapper;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;
    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public GroupVO createGroup(Integer memberId, GroupNicknameVO groupNicknameVO) {
        GroupVO groupVO = new GroupVO(memberId);

        Integer groupInsertingResult = groupMapper.createGroup(groupVO);
        if (groupInsertingResult == 0) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }
        Integer newGroupId = groupVO.getId();
        if (newGroupId == null) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }

        GroupMemberVO groupMemberVO = GroupMemberVO.builder()
                                                   .groupId(newGroupId)
                                                   .memberId(memberId)
                                                   .groupNickname(groupNicknameVO.getGroupNickname())
                                                   .build();

        Integer groupNicknameResult = memberMapper.updateGroupNickname(groupMemberVO);
        if (groupNicknameResult == 0) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }

        return groupVO;
    }
}
