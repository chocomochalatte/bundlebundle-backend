package com.tohome.bundlebundle.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeCartVO {
	private int productId;
	private int memberId;
	private int productCnt;
}