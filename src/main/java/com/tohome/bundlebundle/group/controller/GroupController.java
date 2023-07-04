package com.tohome.bundlebundle.group.controller;

import com.tohome.bundlebundle.common.SimpleDataResponseVO;
import com.tohome.bundlebundle.group.service.GroupService;
import com.tohome.bundlebundle.group.vo.GroupIdVO;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;
import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tohome.bundlebundle.common.Constant.INVITATION_URL;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestHeader("Authorization") String accessToken, @RequestBody GroupNicknameVO groupNicknameVO) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        GroupVO response = groupService.createGroupCart(memberVO.getId(), groupNicknameVO);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/invitation-link")
    public ResponseEntity<?> createInvitationLink(@RequestHeader("Authorization") String accessToken) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        Integer groupId = groupService.findOwningGroupId(memberVO.getId());
        String url = INVITATION_URL + groupId;
        return ResponseEntity.ok(new SimpleDataResponseVO(url));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGroupCart(@RequestHeader("Authorization") String accessToken) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        Integer groupId = groupService.deleteOwningGroup(memberVO.getId());
        return ResponseEntity.ok(new GroupIdVO(groupId));
    }

}
