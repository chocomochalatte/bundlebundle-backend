package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.common.ObjectValidator;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.group.mapper.GroupMapper;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final GroupMapper groupMapper;

    @Override
    @Transactional
    public MemberGroupNicknameVO updateGroupNickname(Integer memberId, GroupNicknameVO groupNicknameVO) {
        checkMemberId(memberId);
        MemberGroupNicknameVO memberGroupNicknameVO = new MemberGroupNicknameVO(memberId, groupNicknameVO);
        Integer result =  memberMapper.updateGroupNickname(memberGroupNicknameVO);
        ObjectValidator.validateQueryResult(result);
        return memberGroupNicknameVO;
    }

    @Override
    public Integer findPresentGroupId(Integer memberId) {
        checkMemberId(memberId);
        return memberMapper.findGroupIdById(memberId);
    }

    @Override
    @Transactional
    public GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO) {
        checkMemberId(memberId);
        groupMemberVO.addMemberId(memberId);

        Integer groupId = memberMapper.findGroupIdById(memberId);
        checkIfGroupNotExists(groupId);

        Integer result = memberMapper.updateGroup(groupMemberVO);
        ObjectValidator.validateQueryResult(result);

        return groupMemberVO;
    }

    @Override
    @Transactional
    public Integer getOutOfGroup(Integer memberId) {
        findMemberById(memberId);

        Integer groupId = memberMapper.findGroupIdById(memberId);
        checkIfGroupExist(groupId);
        checkIfIsGroupMember(memberId);

        Integer result = memberMapper.deleteGroupIdById(memberId);
        ObjectValidator.validateQueryResult(result);

        return groupId;
    }

    private MemberVO findMemberById(Integer memberId) {
        MemberVO memberVO = memberMapper.findMemberById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        return memberVO;
    }

    private void checkMemberId(Integer memberId) {
        log.info("memberID=" + memberId);
        MemberVO memberVO = memberMapper.findMemberById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private void checkIfGroupNotExists(Integer groupId) {
        if (groupId != null) {
            throw new BusinessException(ErrorCode.GROUP_ALREADY_EXIST);
        }
    }

    private void checkIfGroupExist(Integer groupId) {
        if (groupId == null) {
            throw new BusinessException(ErrorCode.MEMBER_GROUP_NOT_FOUND);
        }
    }

    private void checkIfIsGroupMember(Integer memberId) {
        Integer groupOwnerId = groupMapper.findGroupIdByGroupOwnerId(memberId);
        if (groupOwnerId != null) {
            throw new BusinessException(ErrorCode.IS_A_GROUP_OWNER);
        }
    }
}