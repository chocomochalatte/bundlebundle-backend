package com.tohome.bundlebundle.group.service;

import com.tohome.bundlebundle.common.ObjectValidator;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.group.mapper.GroupMapper;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        ObjectValidator.validateNonNullObject(groupId);
        return groupId;
    }

    @Override
    @Transactional
    public Integer deleteOwningGroup(Integer memberId) {
        // 1. 해당 유저가 그룹장인 그룹ID 조회
        Integer groupId = groupMapper.findGroupIdByGroupOwnerId(memberId);
        checkIfMemberIsGroupOwner(groupId);

        // 2. 해당 그룹에 다른 사용자들이 아직 남아있는지 조회
        List<MemberVO> members = memberMapper.findAllByGroupId(groupId);
        checkIfGroupIsEmpty(members);

        // 3. 해당 유저의 group_id 필드 지우기
        Integer memberResult = memberMapper.deleteGroupIdById(memberId);
        ObjectValidator.validateQueryResult(memberResult);

        // 4. 그룹 삭제하기
        Integer groupResult = groupMapper.deleteGroupById(groupId);
        ObjectValidator.validateQueryResult(groupResult);

        return groupId;
    }

    private GroupVO createGroup(Integer memberId) {
        GroupVO groupVO = new GroupVO(memberId);
        Integer groupId = memberMapper.findGroupIdById(memberId);
        checkIfGroupExists(groupId);

        Integer result = groupMapper.createGroup(groupVO);
        ObjectValidator.validateQueryResult(result);
        ObjectValidator.validateNonNullObject(groupVO.getId());
        return groupVO;
    }

    private void updateGroupNickname(GroupMemberVO groupMemberVO) {
        Integer result = memberMapper.updateGroup(groupMemberVO);
        ObjectValidator.validateQueryResult(result);
    }

    private void checkIfGroupExists(Integer groupId) {
        if (groupId != null) {
            throw new BusinessException(ErrorCode.GROUP_ALREADY_EXIST);
        }
    }

    private void checkIfGroupIsEmpty(List<MemberVO> members) {
        if (members != null) {
            throw new BusinessException(ErrorCode.GROUP_ALREADY_EXIST);
        }
    }

    private void checkIfMemberIsGroupOwner(Integer groupId) {
        if (groupId == null) {
            throw new BusinessException(ErrorCode.NOT_A_GROUP_OWNER);
        }
    }

}
