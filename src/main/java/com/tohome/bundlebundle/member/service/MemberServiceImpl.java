package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.common.ObjectValidator;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
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
    public GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO) {
        checkMemberId(memberId);
        groupMemberVO.addMemberId(memberId);
        Integer result = memberMapper.updateGroup(groupMemberVO);
        ObjectValidator.validateQueryResult(result);
        return groupMemberVO;
    }

    @Override
    public void getOutOfGroup(Integer memberId) {
        checkMemberId(memberId);
        Integer result = memberMapper.deleteGroupIdById(memberId);
        ObjectValidator.validateQueryResult(result);
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
}