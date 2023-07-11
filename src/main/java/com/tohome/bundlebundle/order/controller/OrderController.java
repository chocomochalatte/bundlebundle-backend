package com.tohome.bundlebundle.order.controller;

import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.MemberVO;
import com.tohome.bundlebundle.order.service.OrderCartService;
import com.tohome.bundlebundle.order.vo.GroupCartOrderVO;
import com.tohome.bundlebundle.order.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {
    private final JwtTokenUtils jwtTokenUtils;
    private final OrderCartService service;

    // 개인 장바구니 전체 주문
    @PostMapping("/cart")
    public ResponseEntity<MemberVO> orderCart(String token) {
        MemberVO user =jwtTokenUtils.getUserFromJwtToken(token);
        // 구현 필요
        return ResponseEntity.ok(user);
    }

    // 그룹 장바구니 전체 주문
    @PostMapping("/groupcart")
    public ResponseEntity<OrderVO> orderGroupCart(@RequestHeader("Authorization") String token) {
        MemberVO user = jwtTokenUtils.getUserFromJwtToken(token);
        OrderVO result = service.orderGroupCart(user.getId());
        return ResponseEntity.ok().body(result);
    }

    // 나의 주문내역 확인
    @GetMapping("/log")
    public ResponseEntity<List<GroupCartOrderVO>> getLog(@RequestHeader("Authorization") String token) {
        MemberVO user =jwtTokenUtils.getUserFromJwtToken(token);
        List<GroupCartOrderVO> logList = service.getLog(user.getId());
        return ResponseEntity.ok(logList);
    }

    // 현재 보는 상품 바로 구매
    @PostMapping("/direct")
    public ResponseEntity<?> orderDirect(@RequestHeader("Authorization") String token) {
        MemberVO user =jwtTokenUtils.getUserFromJwtToken(token);
        // 구현 필요
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<GroupCartOrderVO>> showOrderResult(@RequestHeader("Authorization") String token, @PathVariable Integer orderId) {
        List<GroupCartOrderVO> orderResult = service.showOrderResult(orderId);
        return ResponseEntity.ok(orderResult);
    }
}
