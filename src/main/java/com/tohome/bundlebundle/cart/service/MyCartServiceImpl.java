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
import com.tohome.bundlebundle.cart.vo.ChangeCartVO;
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
	
	
	// 개인 장바구니 조회해서 출력하는 곳
	@Override
	public List<CartProductVO> showItem(int memberId) {
		List<CartProductVO> result;
		// 오류 체크하는 로직
		int check = mycartMapper.memberCheck(memberId);
		System.out.println("뭐가 뜰까" + check);
		if(check>0) {
			result = mycartMapper.showMyItem(memberId);
			return result;
		}else {
			throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
		}
	}
	
	
	// 개인 장바구니에 상품 조회하기(중복확인)
	@Override
	public CheckVO checkItemCart(CartItemAddVO cartItemAddVO) {
		int productId = cartItemAddVO.getProductId();
		int check = mycartMapper.productCheck(productId);
		if(check>0) {
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
		}else {
			throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
		}
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
		CheckVO checkVO = new CheckVO();
		
		TransactionStatus txStatus =
				transactionManager.getTransaction(
						new DefaultTransactionDefinition());
		
		try {
			mycartMapper.deleteItem(cartItemAddVO);
			transactionManager.commit(txStatus);
			checkVO.setMessage("장바구니 담은 상품이 삭제되었습니다.");
			checkVO.setExists(true);
			return checkVO;
		} catch (Exception e) {
			checkVO.setMessage("장바구니 담은 상품이 삭제되지 않았습니다.");
			checkVO.setExists(false);
			transactionManager.rollback(txStatus);
			return checkVO;
		}
		
		
	}

	// 개인 장바구니에서 상품 수량 변경하는 곳
	@Override
	public void changeProductCnt(ChangeCartVO changeCartVO) {
		TransactionStatus txStatus =
				transactionManager.getTransaction(
						new DefaultTransactionDefinition());
		
		try {
			mycartMapper.changeProductCnt(changeCartVO);
			transactionManager.commit(txStatus);
		}catch (Exception e) {
			transactionManager.rollback(txStatus);
		}
		
	}

	

	

}
