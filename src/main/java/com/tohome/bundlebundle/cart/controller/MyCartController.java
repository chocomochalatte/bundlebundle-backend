package com.tohome.bundlebundle.cart.controller;

import com.tohome.bundlebundle.cart.service.CartService;
import com.tohome.bundlebundle.cart.vo.*;
import com.tohome.bundlebundle.member.util.JwtTokenUtils;
import com.tohome.bundlebundle.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/cart")
public class MyCartController {
	private final CartService service;
	private final JwtTokenUtils jwtTokenUtils;

	//개인 장바구니 조회
	@GetMapping(value = "/individual")
	@ResponseBody
	public ResponseEntity<CartVO> checkCart(@RequestHeader("Authorization") String accessToken){
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		List<CartProductVO> cartProductVO = service.showItem(memberVO.getId());
		CartVO cartvo = CartVO.builder()
							  .cartProducts(cartProductVO)
							  .cartCnt(cartProductVO.size())
							  .build();
		return new ResponseEntity<>(cartvo, HttpStatus.OK);
	}


	// 개인 장바구니에 상품 조회하기 (추가 전에 중복 확인)
	@GetMapping(value = "/check/{productId}")
	public ResponseEntity<CheckVO> checkItemCart(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId){
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		CartItemAddVO cartItemAddVO = CartItemAddVO.builder()
												   .memberId(memberVO.getId())
												   .productId(productId)
												   .build();
		CheckVO result = service.checkItemCart(cartItemAddVO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 개인 장바구니에 상품 추가하기
	@PostMapping(value = "")
	public ResponseEntity<CartItemAddVO> addItemCart(@RequestBody CartItemAddVO cartItemAddVO) {
		service.addCartItem(cartItemAddVO);
		return new ResponseEntity<>(cartItemAddVO, HttpStatus.OK);
	}

	// 개인 장바구니에 상품 삭제하기
	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<CheckVO> deleteCart(@RequestHeader("Authorization") String accessToken, @PathVariable("productId") int productId){
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		CartItemAddVO cartItemAddVO = CartItemAddVO.builder()
												   .memberId(memberVO.getId())
												   .productId(productId)
												   .build();
	    CheckVO result = service.deleteCartItem(cartItemAddVO);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 개인 장바구니에서 상품 수량 변경하기
	@PatchMapping(value = "/{productId}/{productCnt}")
	public ResponseEntity<ChangeCartVO> changeProductCnt(@RequestHeader("Authorization") String accessToken,
														 @PathVariable("productId") int productId, @PathVariable("productCnt") int productCnt){
		MemberVO memberVO = jwtTokenUtils.getUserFromJwtToken(accessToken);
		ChangeCartVO changeCartVO = ChangeCartVO.builder()
								   			   .memberId(memberVO.getId())
									 		   .productId(productId)
											   .productCnt(productCnt)
											   .build();
		service.changeProductCnt(changeCartVO);
		return new ResponseEntity<>(changeCartVO, HttpStatus.OK);
	}
}