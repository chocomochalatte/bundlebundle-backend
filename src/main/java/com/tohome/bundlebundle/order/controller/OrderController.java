package com.tohome.bundlebundle.order.controller;

import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.MemberVO;
import com.tohome.bundlebundle.order.service.OrderCartServiceImpl;
import com.tohome.bundlebundle.order.vo.ProductOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {
    private final JwtTokenUtils jwtTokenUtils;
    private final OrderCartServiceImpl service;

    //개인카트에 당김 장바구니 구매
    @DeleteMapping("/cart/{memberId}")
    public ResponseEntity<MemberVO> orderCart(String token, @PathVariable("memberId") int memberId) {

        MemberVO user =jwtTokenUtils.getUserFromJwtToken(token);
        return ResponseEntity.ok(user);
    }

    //그룹카트에 당김 장바구니 구매
    @DeleteMapping("groupcart/{groupId}")
    public ResponseEntity<String> orderGroupCart(String token, @PathVariable("groupId") int groupId) {

        service.orderGroupCart(groupId);
        String message = "Order GroupCart Success!";
        return ResponseEntity.ok().body(message);
    }

    //주문내역 확인
    @GetMapping("/log")
    public ResponseEntity<List<ProductOrderVO>> getLog(@RequestHeader("Authorization") String token) {
        MemberVO user =jwtTokenUtils.getUserFromJwtToken(token);
        List<ProductOrderVO> logList = service.getLog(user.getId());
        return new ResponseEntity<List<ProductOrderVO>>(logList,HttpStatus.OK);
    }

    //현재 보는 상품 바로 구매
    @PostMapping("/direct")
    public ResponseEntity<MemberVO> orderDirect(String token) {
        //바로 주문
        return null;

    }
}
