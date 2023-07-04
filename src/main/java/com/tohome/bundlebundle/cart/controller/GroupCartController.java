package com.tohome.bundlebundle.cart.controller;


import java.util.List;

import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tohome.bundlebundle.cart.service.GroupCartService;
import com.tohome.bundlebundle.cart.vo.CheckVO;
import com.tohome.bundlebundle.cart.vo.GroupCartItemAddVO;
import com.tohome.bundlebundle.cart.vo.GroupCartListVO;
import com.tohome.bundlebundle.cart.vo.GroupCartVO;
import com.tohome.bundlebundle.cart.vo.GroupChangeCartVO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/cart/group")
public class GroupCartController {
    private final GroupCartService service;
    private final JwtTokenUtils jwtTokenUtils;

    //개인 장바구니 조회
    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupCartListVO> showItems(@RequestHeader("Authorization") String accessToken) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		Integer groupId = memberVO.getGroupId();

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
    @GetMapping(value = "check/{memberId}/{productId}/{groupId}")
    public ResponseEntity<CheckVO> checkGroupItemCart(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId) {
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		GroupCartItemAddVO groupCartItemAddVO = GroupCartItemAddVO.builder()
																  .memberId(memberVO.getId())
															  	  .productId(productId)
																  .groupId(memberVO.getGroupId())
															  	  .build();
        CheckVO result = service.groupCheckItemCart(groupCartItemAddVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //그룹 장바구니에 상품 추가하기
    @PostMapping(value = "")
    public ResponseEntity<GroupCartItemAddVO> addItemCart(@RequestBody GroupCartItemAddVO groupCartItemAddVO) {
        service.addGroupCartItem(groupCartItemAddVO);
        return new ResponseEntity<>(groupCartItemAddVO, HttpStatus.OK);
    }

    //그룹 장바구니에서 상품 삭제하기
    @DeleteMapping(value = "{memberId}/{productId}/{groupId}")
    public ResponseEntity<CheckVO> deleteGroupCart(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId) {
        MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        GroupCartItemAddVO groupCartItemAddVO = GroupCartItemAddVO.builder()
                                                                  .memberId(memberVO.getId())
                                                                  .groupId(memberVO.getGroupId())
                                                                  .productId(productId)
                                                                  .build();
        CheckVO result = service.deleteGroupCartItem(groupCartItemAddVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 그룹 장바구니에서 상품 수량 변경하기
    @PatchMapping(value = "{memberId}/{productId}/{groupId}/{productCnt}")
    public ResponseEntity<GroupChangeCartVO> changeGroupProductCnt(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId, @PathVariable("productCnt") int productCnt) {
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
        GroupChangeCartVO groupChangeCartVO = GroupChangeCartVO.builder()
                                                               .memberId(memberVO.getId())
                                                               .groupId(memberVO.getGroupId())
                                                               .productId(productId)
                                                               .productCnt(productCnt)
                                                               .build();
        service.changeGroupProductCnt(groupChangeCartVO);
        return new ResponseEntity<>(groupChangeCartVO, HttpStatus.OK);
    }
}
