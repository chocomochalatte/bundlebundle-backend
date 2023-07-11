package com.tohome.bundlebundle.member.mapper;

import com.tohome.bundlebundle.member.vo.MemberVO;
import org.springframework.lang.NonNull;
import org.apache.ibatis.annotations.Mapper;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;
import java.util.List;
import java.util.Optional;


@Mapper
public interface MemberMapper {

    Optional<MemberVO> findMemberById(Integer memberId);

    Optional<MemberVO> findUserByEmail(String email);

    Optional<Integer> findGroupIdById(Integer memberId);

    Integer updateGroup(GroupMemberVO groupMemberVO);

    Integer updateGroupNickname(MemberGroupNicknameVO memberGroupNicknameVO);

    Integer deleteGroupIdById(Integer memberId);

    List<MemberVO> findAllByGroupId(Integer groupId);

    Boolean insertUser(@NonNull MemberVO member) throws Exception;

    Integer updateUser(@NonNull MemberVO member) throws Exception;

	String findFcmToken(Integer groupId);
}
