package com.tohome.bundlebundle.member.controller;

import com.tohome.bundlebundle.BooleanResponseVO;
import com.tohome.bundlebundle.TempTokenUtil;
import com.tohome.bundlebundle.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TempTokenUtil tokenUtil;

    @GetMapping("/group-check")
    public ResponseEntity<?> hasGroupCart(@RequestHeader("Authorization") String accessToken) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        boolean result = memberService.hasGroupCart(memberId);
        return ResponseEntity.ok(new BooleanResponseVO(result));
    }

}
