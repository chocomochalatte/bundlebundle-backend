package com.tohome.bundlebundle.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductVO {
	private int memberId;
	private int cartId;
    private int productId;
    private String productOrigin;
    private String productBrand;
    private String productName;
    private String productThumbnailImg;
    private int productPrice;
    private int productCnt;
}
