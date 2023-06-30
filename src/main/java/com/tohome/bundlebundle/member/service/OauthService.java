package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.member.vo.MemberVO;
import com.tohome.bundlebundle.member.vo.OauthTokenVO;

public interface OauthService {

    /**
     * Member를 생성하는 서비스
     *
     * @param : OauthTokenVO
     * @return : 정상적으로 처리한 여부, MemberVO
     * @throws : 정상적인 리턴을 받지 못한 경우 exception
     * @version : 1.0.0
     * @author : noino
     * @see : MemberService#loginOauthService(OauthTokenVO tokenVO)) 토큰을 통해 로그인을 진행한다
     */
    MemberVO loginOauthService (OauthTokenVO tokenVO);
}
