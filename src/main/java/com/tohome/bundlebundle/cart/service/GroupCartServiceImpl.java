package com.tohome.bundlebundle.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.tohome.bundlebundle.cart.mapper.MyCartMapper;
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
}
