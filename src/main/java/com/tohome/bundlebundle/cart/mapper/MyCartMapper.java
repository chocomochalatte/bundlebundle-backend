package com.tohome.bundlebundle.cart.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.tohome.bundlebundle.cart.vo.CartItemAddVO;
import com.tohome.bundlebundle.cart.vo.CartProductVO;

@Mapper
public interface MyCartMapper {
	List<CartProductVO> showMyItem(int memberId);
	
	int addCartItem(CartItemAddVO cartItemAddVO);

	CartProductVO checkItemCart(CartItemAddVO cartItemAddVO);
}
