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
    public GroupVO createGroupCart(Integer memberId, GroupNicknameVO groupNicknameVO) {

        // 1. 그룹 생성
        GroupVO createdGroupVO = createGroup(memberId);

        // 2. 그룹 닉네임 업데이트
        GroupMemberVO groupMemberVO = new GroupMemberVO(createdGroupVO, groupNicknameVO);
        updateGroupNickname(groupMemberVO);

        // 3. 생성된 그룹 정보 리턴
        return createdGroupVO;
    }

    @Override
    public Integer findOwningGroupId(Integer memberId) {
        Integer groupId = groupMapper.findGroupIdByGroupOwnerId(memberId);
        validateNonNullObject(groupId);
        return groupId;
    }

    private GroupVO createGroup(Integer memberId) {
        GroupVO groupVO = new GroupVO(memberId);
        Integer result = groupMapper.createGroup(groupVO);
        validateQueryResult(result);
        validateNonNullObject(groupVO.getId());
        return groupVO;
    }

    private void updateGroupNickname(GroupMemberVO groupMemberVO) {
        Integer result = memberMapper.updateMemberGroup(groupMemberVO);
        validateQueryResult(result);
    }

    private void validateQueryResult(Integer result) {
        if (result == 0) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }
    }

    private void validateNonNullObject(Object object) {
        if (object == null) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }
    }

}
