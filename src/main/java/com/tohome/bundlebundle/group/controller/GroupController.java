package com.tohome.bundlebundle.group.controller;

import com.tohome.bundlebundle.common.SimpleDataResponseVO;
import com.tohome.bundlebundle.common.TempTokenUtil;
import com.tohome.bundlebundle.group.service.GroupService;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;
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
    private final TempTokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestHeader("Authorization") String accessToken, @RequestBody GroupNicknameVO groupNicknameVO) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        GroupVO response = groupService.createGroupCart(memberId, groupNicknameVO);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/invitation-link")
    public ResponseEntity<?> createInvitationLink(@RequestHeader("Authorization") String accessToken) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        Integer groupId = groupService.findOwningGroupId(memberId);
        String url = INVITATION_URL + groupId;
        return ResponseEntity.ok(new SimpleDataResponseVO(url));
    }

}
