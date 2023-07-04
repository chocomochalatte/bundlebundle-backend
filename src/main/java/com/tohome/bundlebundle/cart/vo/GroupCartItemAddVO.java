package com.tohome.bundlebundle.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCartItemAddVO {
	private int productId;
	private int memberId;
	private int groupId;
}
