package com.tohome.bundlebundle.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.tohome.bundlebundle.cart.mapper.MyCartMapper;
import com.tohome.bundlebundle.cart.vo.CheckVO;
import com.tohome.bundlebundle.cart.vo.GroupCartItemAddVO;
import com.tohome.bundlebundle.cart.vo.GroupCartProductVO;
import com.tohome.bundlebundle.cart.vo.GroupCartVO;
import com.tohome.bundlebundle.cart.vo.GroupVO;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GroupCartServiceImpl implements GroupCartService{
	
	private final MyCartMapper mycartMapper;
	
	@Autowired
	private PlatformTransactionManager transactionManager;


	@Override
	public List<GroupCartVO> checkMember(int groupId) {
		List<GroupCartVO> result = new ArrayList<GroupCartVO>();
		
		int check = mycartMapper.groupCheck(groupId);
		if(check>0) {
			//group 장바구니의 memberId 알아내는 곳
			List<Integer> member = mycartMapper.checkMember(groupId);
			
			for(int memberId : member) {
				GroupCartVO groupCartVO = new GroupCartVO();
				String groupNickName = mycartMapper.getnickName(memberId);
				List<GroupCartProductVO> groupCartProductVO = mycartMapper.showGroupItem(memberId);
				groupCartVO.setMemberId(memberId);
				groupCartVO.setCartCnt(groupCartProductVO.size());
				groupCartVO.setCartProducts(groupCartProductVO);
				groupCartVO.setGroupNickname(groupNickName);
				result.add(groupCartVO);
			}
			return result;
		}else {
			throw new BusinessException(ErrorCode.GROUP_NOT_FOUND);
		}
	}

	
	// 그룹 장바구니에 상품 조회하기 (추가 전에 중복 확인)
	@Override
	public CheckVO groupCheckItemCart(GroupCartItemAddVO groupCartItemAddVO) {
		int productId = groupCartItemAddVO.getProductId();
		int memberId = groupCartItemAddVO.getMemberId();
		int groupId = groupCartItemAddVO.getGroupId();
		
		int productCheck = mycartMapper.productCheck(productId);
		int memberCheck = mycartMapper.memberCheck(memberId);
		int groupCheck = mycartMapper.groupCheck(groupId);
		
		if(productCheck>0 && memberCheck>0 && groupCheck>0) {
			CheckVO checkVO = new CheckVO();
			int result = mycartMapper.groupCheckItemCart(groupCartItemAddVO);
			if(result>0) {
				checkVO.setExists(true);
				checkVO.setMessage("해당 상품이 그룹 내 장바구니에 존재합니다.");
			}else {
				checkVO.setExists(false);
				checkVO.setMessage("해당 상품이 그룹 내 장바구니에 존재하지 않습니다.");
			}
			return checkVO;
		}else {
			if(productCheck==0) throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
			else if(memberCheck==0) throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
			else throw new BusinessException(ErrorCode.GROUP_NOT_FOUND);
		}
	
	}
	
	
	
	//그룹 장바구니에 상품 추가하기
	@Override
	public void addGroupCartItem(GroupCartItemAddVO groupCartItemAddVO) {
		TransactionStatus txStatus =
				transactionManager.getTransaction(
						new DefaultTransactionDefinition());
		int productId = groupCartItemAddVO.getProductId();
		int memberId = groupCartItemAddVO.getMemberId();
		int groupId = groupCartItemAddVO.getGroupId();
		
		int productCheck = mycartMapper.productCheck(productId);
		int memberCheck = mycartMapper.memberCheck(memberId);
		int groupCheck = mycartMapper.groupCheck(groupId);
		
		if(productCheck>0 && memberCheck>0 && groupCheck>0) {
			try {
				mycartMapper.addGroupCartItem(groupCartItemAddVO);
				transactionManager.commit(txStatus);
			} catch (Exception e) {
				transactionManager.rollback(txStatus);
			}
		}else {
			if(productCheck==0) throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
			else if(memberCheck==0) throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
			else throw new BusinessException(ErrorCode.GROUP_NOT_FOUND);
		}
	}



	
}
