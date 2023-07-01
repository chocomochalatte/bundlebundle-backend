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
import com.tohome.bundlebundle.cart.vo.CheckVO;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;

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
		if(result==null) {
			throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
		}else {
			return result;
		}
		
	}
	
	
	// 개인 장바구니에 상품 조회하기(중복확인)
	@Override
	public CheckVO checkItemCart(CartItemAddVO cartItemAddVO) {
		CheckVO checkVO = new CheckVO();
		CartProductVO result = mycartMapper.checkItemCart(cartItemAddVO);
		if(result!=null) {
			checkVO.setExists(true);
			checkVO.setMessage("해당 상품이 장바구니에 이미 존재합니다.");
		}else {
			checkVO.setExists(false);
			checkVO.setMessage("해당 상품이 장바구니에 존재하지 않습니다.");
		}
		return checkVO;
	}
	
	// 개인 장바구니에 아이템 추가하는 곳
	@Override
	public int addCartItem(CartItemAddVO cartItemAddVO) {
		TransactionStatus txStatus =
				transactionManager.getTransaction(
						new DefaultTransactionDefinition());
		
		try {
			mycartMapper.addCartItem(cartItemAddVO);
			transactionManager.commit(txStatus);
			return 1;
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			return 0;
		}
		
	}

	// 개인 장바구니에서 상품 삭제하는 곳
	@Override
	public CheckVO deleteCartItem(CartItemAddVO cartItemAddVO) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
