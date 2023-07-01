package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
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
    public boolean hasGroupCart(Integer memberId) {
        findMemberById(memberId);
        return memberMapper.findGroupIdById(memberId).isPresent();
    }

    private MemberVO findMemberById(Integer memberId) {
        MemberVO memberVO = memberMapper.findMemberById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        return memberVO;
    }

    @Override
    public GroupMemberVO joinGroup(Integer memberId, GroupMemberVO groupMemberVO) {
        groupMemberVO.updateMemberId(memberId);
        memberMapper.updateMemberGroup(groupMemberVO);
        return groupMemberVO;
    }

}
