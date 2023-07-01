package com.tohome.bundlebundle.cart.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupCartVO {
	private int memberId;
	private int cartCnt;
	private String groupNickname;
	private List<GroupCartProductVO> cartProducts;
}
