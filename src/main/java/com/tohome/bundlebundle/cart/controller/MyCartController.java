package com.tohome.bundlebundle.cart.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tohome.bundlebundle.cart.service.CartService;
import com.tohome.bundlebundle.cart.vo.CartItemAddVO;
import com.tohome.bundlebundle.cart.vo.CartProductVO;
import com.tohome.bundlebundle.cart.vo.CartVO;
import com.tohome.bundlebundle.cart.vo.ChangeCartVO;
import com.tohome.bundlebundle.cart.vo.CheckVO;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/cart")
public class MyCartController {
	
	private final CartService service;
	
	
	//개인 장바구니 조회
	@GetMapping(value = "individual/{memberId}")
	@ResponseBody
	public ResponseEntity<CartVO> checkCart(@PathVariable int memberId){
		
		List<CartProductVO> cartProductVO = service.showItem(memberId);
		CartVO cartvo = new CartVO();
		cartvo.setCartProducts(cartProductVO);
		cartvo.setCartCnt(cartProductVO.size());
		return new ResponseEntity<CartVO>(cartvo, HttpStatus.OK);
	}
	
	
	// 개인 장바구니에 상품 조회하기 (추가 전에 중복 확인)
	@GetMapping(value = "check/{memberId}/{productId}")
	public ResponseEntity<CheckVO> checkItemCart(@PathVariable("memberId") int memberId, @PathVariable("productId") int productId){
		CartItemAddVO cartItemAddVO = new CartItemAddVO();
	    cartItemAddVO.setMemberId(memberId);
	    cartItemAddVO.setProductId(productId);
		CheckVO result = service.checkItemCart(cartItemAddVO);
		return new ResponseEntity<CheckVO>(result, HttpStatus.OK);
	}
	
	// 개인 장바구니에 상품 추가하기
	@PostMapping(value = "")
	public ResponseEntity<CartItemAddVO> addItemCart(@RequestBody CartItemAddVO cartItemAddVO){
		int result = service.addCartItem(cartItemAddVO);
		
		if(result>0) {
			CartItemAddVO success = cartItemAddVO;
			return new ResponseEntity<CartItemAddVO>(success, HttpStatus.OK);
		}else {
			return null;
		}
	}
	
	// 개인 장바구니에 상품 삭제하기
	@DeleteMapping(value = "{memberId}/{productId}")
	public ResponseEntity<CheckVO> deleteCart(@PathVariable("memberId") int memberId, @PathVariable("productId") int productId){
	    CartItemAddVO cartItemAddVO = new CartItemAddVO();
	    cartItemAddVO.setMemberId(memberId);
	    cartItemAddVO.setProductId(productId);
	    CheckVO result = service.deleteCartItem(cartItemAddVO);
		return new ResponseEntity<CheckVO>(result, HttpStatus.OK);
	}
	
	// 개인 장바구니에서 상품 수량 변경하기
	@PatchMapping(value = "{memberId}/{productId}/{productCnt}")
	public ResponseEntity<ChangeCartVO> changeProductCnt(@PathVariable("memberId") int memberId, 
			@PathVariable("productId") int productId, @PathVariable("productCnt") int productCnt){
		ChangeCartVO changeCartVO = new ChangeCartVO();
		changeCartVO.setMemberId(memberId);
		changeCartVO.setProductId(productId);
		changeCartVO.setProductCnt(productCnt);
		
		service.changeProductCnt(changeCartVO);
		
		ChangeCartVO result = changeCartVO;
		
		return new ResponseEntity<ChangeCartVO>(result, HttpStatus.OK);
	}
}