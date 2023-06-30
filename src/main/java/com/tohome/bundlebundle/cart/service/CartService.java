package com.tohome.bundlebundle.cart.service;


import java.util.List;


import com.tohome.bundlebundle.cart.vo.CartItemAddVO;
import com.tohome.bundlebundle.cart.vo.CartProductVO;


public interface CartService {
	
	//개인 장바구니 조회해서 출력하는 곳
	List<CartProductVO> showItem(int memberId);

	int AddCartItem(CartItemAddVO cartItemAddVO);

	int DeleteCartItem(int productId);

	

}
