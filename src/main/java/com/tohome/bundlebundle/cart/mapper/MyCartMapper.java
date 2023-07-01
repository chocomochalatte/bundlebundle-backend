package com.tohome.bundlebundle.cart.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.tohome.bundlebundle.cart.vo.CartItemAddVO;
import com.tohome.bundlebundle.cart.vo.CartProductVO;
import com.tohome.bundlebundle.cart.vo.ChangeCartVO;

@Mapper
public interface MyCartMapper {
	
	int memberCheck(int memberId);
	List<CartProductVO> showMyItem(int memberId);
	
	
	int addCartItem(CartItemAddVO cartItemAddVO);

	CartProductVO checkItemCart(CartItemAddVO cartItemAddVO);

	void deleteItem(CartItemAddVO cartItemAddVO);

	void changeProductCnt(ChangeCartVO changeCartVO);

	
}
