package com.tohome.bundlebundle.member.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
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
        
        //checkIfGroupNotExists(groupId);
        
        //fcm 코드 추가
        String token = memberMapper.findFcmToken(groupId);
        System.out.println("fdfdfd" + token);
        if(token !=null) {
        	Message message = Message.builder()
                    .putData("title", groupMemberVO.getGroupNickname() + "님이 장바구니에 들어왔습니다.")
                    .putData("body", "환영해주세요!!")
                    .setToken(token)
                    .build();
        	try {
                String response = FirebaseMessaging.getInstance().send(message);
                log.info("response = " + response);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
       
        }
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

    public MemberVO findUserByEmail(String email) {
        MemberVO memberVO = memberMapper.findUserByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        return memberVO;
    }

    public Boolean insertUser(@NonNull MemberVO member) throws Exception {
        Boolean result = memberMapper.insertUser(member);
        return result;
    }

    public MemberVO updateUser(@NonNull MemberVO member) throws Exception {
        Integer result = memberMapper.updateUser(member);
        return member;
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