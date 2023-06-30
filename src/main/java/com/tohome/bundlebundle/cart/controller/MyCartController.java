package com.tohome.bundlebundle.cart.controller;

import java.util.List;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
	
	// 개인 장바구니에 상품 추가하기
	@PostMapping(value = "")
	public ResponseEntity<CartItemAddVO> addItemCart(@RequestBody CartItemAddVO cartItemAddVO){
		System.out.println("넘어온 값" + cartItemAddVO);
		int result = service.addCartItem(cartItemAddVO);
		
		if(result>0) {
			CartItemAddVO success = cartItemAddVO;
			return new ResponseEntity<CartItemAddVO>(success, HttpStatus.OK);
		}else {
			return null;
		}
	}
	
	// 개인 장바구니에 상품 삭제하기
	@DeleteMapping(value = "{productId}")
	@ResponseBody
	public ResponseEntity<String> deleteCart(@PathVariable int productId){
		System.out.println("안드로이드에서 값이 왔어요!!" + productId);
		int check = service.deleteCartItem(productId);

		String message;
		if(check>0) {
			message="장바구니 담은 상품이 삭제되었습니다";
		}else {
			message="장바구니 담음 상품이 삭제되지 않았습니다.";
		}
		
		//반환할 때 한글이 깨진 경우 이용
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
	    return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	// 개인 장바구니에서 상품 수량 변경하기
}