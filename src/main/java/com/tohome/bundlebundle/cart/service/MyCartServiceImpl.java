package com.tohome.bundlebundle.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tohome.bundlebundle.cart.mapper.MyCartMapper;
import com.tohome.bundlebundle.cart.vo.CartItemAddVO;
import com.tohome.bundlebundle.cart.vo.CartProductVO;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyCartServiceImpl implements CartService{
	
	private final MyCartMapper mycartMapper;
	
	// 개인 장바구니 조회해서 출력하는 곳
	@Override
	public List<CartProductVO> showItem(int memberId) {
		List<CartProductVO> result;
		try {
			result = mycartMapper.showMyItem(memberId);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int AddCartItem(CartItemAddVO cartItemAddVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int DeleteCartItem(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
