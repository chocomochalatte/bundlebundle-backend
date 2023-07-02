package com.tohome.bundlebundle.cart.vo;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckVO {
	private boolean exists;
	private String message;
}
