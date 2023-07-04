package com.tohome.bundlebundle.cart.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {
	private int cartCnt;
	private List<CartProductVO> cartProducts;
}
