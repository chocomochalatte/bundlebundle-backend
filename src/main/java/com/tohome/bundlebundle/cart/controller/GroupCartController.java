package com.tohome.bundlebundle.cart.controller;


import com.tohome.bundlebundle.cart.service.GroupCartService;
import com.tohome.bundlebundle.cart.vo.*;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.member.service.MemberService;
import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cart/group")
public class GroupCartController {
    private final GroupCartService service;
    private final MemberService memberService;
    private final JwtTokenUtils jwtTokenUtils;

    //그룹 장바구니 조회
    @GetMapping
    public ResponseEntity<GroupCartListVO> showItems(@RequestHeader("Authorization") String accessToken) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		Integer groupId = Optional.ofNullable(memberService.findPresentGroupId(memberVO.getId()))
                .orElseThrow(() -> new BusinessException(ErrorCode.GROUP_NOT_FOUND));

        // 1. 팀 내에 어떤 멤버가 있는지 확인
        List<GroupCartVO> groupCartVO = service.checkMember(groupId);
        GroupCartListVO groupVO = GroupCartListVO.builder()
                                                 .groupCart(groupCartVO)
                                                 .totalCnt(groupCartVO.size())
                                                 .memberId(memberVO.getId())
                                                 .build();
        return new ResponseEntity<>(groupVO, HttpStatus.OK);
    }

    // 그룹 장바구니에 상품 조회하기 (추가전에 중복확인)
    @GetMapping(value = "/check/{productId}")
    public ResponseEntity<CheckVO> checkGroupItemCart(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId) {
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        Integer groupId = memberService.findPresentGroupId(memberVO.getId());
        GroupCartItemAddVO groupCartItemAddVO = GroupCartItemAddVO.builder()
																  .memberId(memberVO.getId())
															  	  .productId(productId)
                                                                  .groupId(groupId)
															  	  .build();
        CheckVO result = service.groupCheckItemCart(groupCartItemAddVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //그룹 장바구니에 상품 추가하기
    @PostMapping
    public ResponseEntity<GroupCartItemAddVO> addItemCart(@RequestHeader("Authorization") String accessToken, @RequestBody GroupCartItemAddVO groupCartItemAddVO) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        Integer groupId = memberService.findPresentGroupId(memberVO.getId());
        groupCartItemAddVO.setMemberId(memberVO.getId());
        groupCartItemAddVO.setGroupId(groupId);
        service.addGroupCartItem(groupCartItemAddVO);
        return new ResponseEntity<>(groupCartItemAddVO, HttpStatus.OK);
    }

    //그룹 장바구니에서 상품 삭제하기
    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<CheckVO> deleteGroupCart(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        Integer groupId = memberService.findPresentGroupId(memberVO.getId());
        GroupCartItemAddVO groupCartItemAddVO = GroupCartItemAddVO.builder()
                                                                  .memberId(memberVO.getId())
                                                                  .groupId(groupId)
                                                                  .productId(productId)
                                                                  .build();
        CheckVO result = service.deleteGroupCartItem(groupCartItemAddVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 그룹 장바구니에서 상품 수량 변경하기
    @PatchMapping(value = "/{productId}/{productCnt}")
    public ResponseEntity<GroupChangeCartVO> changeGroupProductCnt(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId, @PathVariable("productCnt") int productCnt) {
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        Integer groupId = memberService.findPresentGroupId(memberVO.getId());
        GroupChangeCartVO groupChangeCartVO = GroupChangeCartVO.builder()
                                                               .memberId(memberVO.getId())
                                                               .groupId(groupId)
                                                               .productId(productId)
                                                               .productCnt(productCnt)
                                                               .build();
        service.changeGroupProductCnt(groupChangeCartVO);
        return new ResponseEntity<>(groupChangeCartVO, HttpStatus.OK);
    }
}
