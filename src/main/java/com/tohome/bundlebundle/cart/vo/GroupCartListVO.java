package com.tohome.bundlebundle.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCartListVO {
	private int totalCnt;
	private int memberId;	//로그인한 멤버아이디
	private List<GroupCartVO> groupCart;
}
