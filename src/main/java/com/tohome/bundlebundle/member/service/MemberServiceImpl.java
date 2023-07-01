package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.group.vo.GroupIdVO;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
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
    public Integer findPresentGroupId(Integer memberId) {
        validateMemberId(memberId);
        return memberMapper.findGroupIdById(memberId);
    }

    @Override
    public GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO) {
        validateMemberId(memberId);
        groupMemberVO.addMemberId(memberId);
        memberMapper.updateMemberGroup(groupMemberVO);
        return groupMemberVO;
    }

    private MemberVO findMemberById(Integer memberId) {
        MemberVO memberVO = memberMapper.findMemberById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        return memberVO;
    }

    private void validateMemberId(Integer memberId) {
        memberMapper.findMemberById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
