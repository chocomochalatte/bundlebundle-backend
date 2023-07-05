package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.member.mapper.MemberMapper;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService {

    private final KakaoClient kakaoClient;
    private final MemberMapper memberMapper;

    @Override
    public MemberVO loginOauthService(String token) throws Exception {
        MemberVO user = kakaoClient.getUser(token);
        //이메일에 의해서 찾아오기
        Mapper mapper;
        Optional<MemberVO> selectedUser = memberMapper.findUserByEmail(user.getEmail());
        if(selectedUser.isPresent()) {//가입이 되어있는 경우
            //memberMapper.updateUser(user);
            //user.setUserSeq(selectedUser.getUserSeq());
        } else { //첫 로그인
            memberMapper.insertUser(user);
        }
        user.setGroupId(selectedUser.get().getGroupId());
        user.setId(selectedUser.get().getId());
        return user;
    };
}
