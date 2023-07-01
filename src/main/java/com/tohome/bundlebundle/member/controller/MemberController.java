package com.tohome.bundlebundle.member.controller;

import com.tohome.bundlebundle.common.TempTokenUtil;
import com.tohome.bundlebundle.group.vo.GroupIdVO;
import com.tohome.bundlebundle.group.vo.GroupMemberVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.member.service.MemberService;
import com.tohome.bundlebundle.member.vo.MemberGroupNicknameVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final TempTokenUtil tokenUtil;

    @PatchMapping("/group")
    public ResponseEntity<?> updateGroupNickname(@RequestHeader("Authorization") String accessToken, @RequestBody GroupNicknameVO groupNicknameVO) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        MemberGroupNicknameVO response = memberService.updateGroupNickname(memberId, groupNicknameVO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/group-check")
    public ResponseEntity<?> hasGroupCart(@RequestHeader("Authorization") String accessToken) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        Integer groupId = memberService.findPresentGroupId(memberId);
        return ResponseEntity.ok(new GroupIdVO(groupId));
    }

    @PostMapping("/group-join")
    public ResponseEntity<?> joinGroup(@RequestHeader("Authorization") String accessToken, @RequestBody GroupMemberVO groupMemberVO) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        GroupMemberVO response = memberService.joinGroup(memberId, groupMemberVO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/group-out")
    public ResponseEntity<?> getOutOfGroup(@RequestHeader("Authorization") String accessToken) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        memberService.getOutOfGroup(memberId);
        return ResponseEntity.ok(null);

    }

}
