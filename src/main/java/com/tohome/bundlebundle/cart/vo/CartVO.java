package com.tohome.bundlebundle.cart.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {
	private int cartCnt;
	private List<CartProductVO> cart_products;
}
