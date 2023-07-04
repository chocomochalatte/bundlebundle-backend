package com.tohome.bundlebundle.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCartProductVO {
	private int groupId;
	private int memberId;
	private int productId;
	private String productOrigin;
	private String productBrand;
	private String productName;
	private String productThumbnailImg;
	private	int productPrice;
	private int productCnt;
	private int discountRate;
}
