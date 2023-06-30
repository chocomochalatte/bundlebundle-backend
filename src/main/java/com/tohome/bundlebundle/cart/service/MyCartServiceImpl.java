package com.tohome.bundlebundle.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	/*
	 * //Transaction시작 TransactionStatus txStatus =
	 * transactionManager.getTransaction( new DefaultTransactionDefinition()); try {
	 * dao.reviewUpdate(reviewWriteVO); transactionManager.commit(txStatus); }catch
	 * (Exception e) { transactionManager.rollback(txStatus); e.printStackTrace(); }
	 */
	
	// 개인 장바구니 조회해서 출력하는 곳
	@Override
	public List<CartProductVO> showItem(int memberId) {
		List<CartProductVO> result;
			result = mycartMapper.showMyItem(memberId);
			return result;
	
	}

	@Override
	public int addCartItem(CartItemAddVO cartItemAddVO) {
		TransactionStatus txStatus =
				transactionManager.getTransaction(
						new DefaultTransactionDefinition());
		
		try {
			mycartMapper.addCartItem(cartItemAddVO);
			transactionManager.commit(txStatus);
			System.out.println("여기까지 왔어요11");
			return 1;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			System.out.println("여기까지 왔어요22");
			return 0;
		}
		
	}

	@Override
	public int deleteCartItem(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
