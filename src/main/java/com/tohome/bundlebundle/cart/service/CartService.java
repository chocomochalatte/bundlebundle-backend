package com.tohome.bundlebundle.cart.service;


import java.util.List;


import com.tohome.bundlebundle.cart.vo.CartItemAddVO;
import com.tohome.bundlebundle.cart.vo.CartProductVO;
import com.tohome.bundlebundle.cart.vo.ChangeCartVO;
import com.tohome.bundlebundle.cart.vo.CheckVO;


public interface CartService {
	
	//개인 장바구니 조회해서 출력하는 곳
	List<CartProductVO> showItem(int memberId);
	
	// 개인 장바구니에 상품 조회하기(중복확인)
	CheckVO checkItemCart(CartItemAddVO cartItemAddVO);
	
	// 개인 장바구니에 아이템 추가하는 곳
	int addCartItem(CartItemAddVO cartItemAddVO);
	
	// 개인 장바구니에 아이템 삭제하는 곳
	CheckVO deleteCartItem(CartItemAddVO cartItemAddVO);
	
	
	// 개인 장바구니에서 상품 수량 변경하는 곳
	void changeProductCnt(ChangeCartVO changeCartVO);

	

	

}
