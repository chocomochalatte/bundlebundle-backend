package com.tohome.bundlebundle.member.mapper;

import com.tohome.bundlebundle.member.vo.MemberVO;
import com.tohome.bundlebundle.member.vo.OauthTokenVO;
import org.springframework.lang.NonNull;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
    Boolean insertOneMember(@NonNull MemberVO member) throws Exception;
    OauthTokenVO selectKakaoOneMemberByToken(@NonNull OauthTokenVO token) throws Exception;
}