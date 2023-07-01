package com.tohome.bundlebundle.group.controller;

import com.tohome.bundlebundle.TempTokenUtil;
import com.tohome.bundlebundle.group.service.GroupService;
import com.tohome.bundlebundle.group.vo.GroupNicknameVO;
import com.tohome.bundlebundle.group.vo.GroupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final TempTokenUtil tokenUtil;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestHeader("Authorization") String accessToken, @RequestBody GroupNicknameVO groupNicknameVO) {
        Integer memberId = tokenUtil.extractMemberId(accessToken);
        GroupVO groupVO = groupService.createGroup(memberId, groupNicknameVO);
        return ResponseEntity.ok(groupVO);
    }

}
