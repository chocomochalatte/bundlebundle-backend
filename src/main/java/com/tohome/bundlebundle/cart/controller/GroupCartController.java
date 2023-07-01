package com.tohome.bundlebundle.cart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tohome.bundlebundle.cart.service.CartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/cart/group")
public class GroupCartController {
	private final CartService service;
	
	// 그룹 장바구니 조회
}
