package com.tohome.bundlebundle.member.service;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
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
        return memberMapper.hasGroupCart(memberId).isPresent();
    }
}
