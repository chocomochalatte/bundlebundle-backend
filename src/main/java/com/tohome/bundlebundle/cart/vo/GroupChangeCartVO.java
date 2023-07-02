package com.tohome.bundlebundle.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupChangeCartVO {
	private int memberId;
	private int productId;
	private int groupId;
	private int productCnt;
}
