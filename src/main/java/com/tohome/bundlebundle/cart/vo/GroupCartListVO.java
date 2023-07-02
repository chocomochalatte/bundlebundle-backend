package com.tohome.bundlebundle.cart.vo;

import java.util.List;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCartListVO {
	private int totalCnt;
	private List<GroupCartVO> groupCart;
}
