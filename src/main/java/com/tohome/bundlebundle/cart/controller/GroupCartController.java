package com.tohome.bundlebundle.cart.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tohome.bundlebundle.cart.service.GroupCartService;
import com.tohome.bundlebundle.cart.vo.GroupCartVO;
import com.tohome.bundlebundle.cart.vo.GroupVO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/cart/group")
public class GroupCartController {
	private final GroupCartService service;
	
	//개인 장바구니 조회
		@GetMapping(value = "{groupId}")
		public ResponseEntity<GroupVO> showItmes(@PathVariable int groupId){
			
			GroupVO groupVO = new GroupVO();
			//일단 어떤 멤버가 있는지 확인하면서 리스트 출력
			List<GroupCartVO> groupCartVO = service.checkMember(groupId);
			groupVO.setGroupCart(groupCartVO);
			groupVO.setTotalCnt(groupCartVO.size());
			return new ResponseEntity<GroupVO>(groupVO, HttpStatus.OK);
		}
}
