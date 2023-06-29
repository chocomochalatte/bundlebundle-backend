package com.tohome.bundlebundle.cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tohome.bundlebundle.cart.vo.CartProductVO;
import com.tohome.bundlebundle.cart.vo.CartVO;

@RestController
@RequestMapping(value = "api/cart")
public class MyCartController {
	
	@PostMapping(value = "individual/{id}")
	@ResponseBody
	public ResponseEntity<CartVO> inquireCart(@PathVariable int id){
		System.out.println("안드로이드에서 값이 왔어요!!" + id);
		CartVO result = new CartVO();
		List<CartProductVO> aList = new ArrayList<>();
		CartProductVO test = new CartProductVO();
		test.setCartId(1);
		test.setProductBrand("아름다운 상관성");
		test.setProductCnt(2);
		test.setProductId(2);
		test.setProductName("한우 1등급");
		test.setProductOrigin("부산");
		test.setProductPrice(30000);
		test.setProductThumbnailImg("wwww.fdfdfjjsjfl.png");
		aList.add(test);
		result.setCartCnt(2);
		result.setCart_products(aList);
		
		return new ResponseEntity<CartVO>(result, HttpStatus.OK);
	}
}