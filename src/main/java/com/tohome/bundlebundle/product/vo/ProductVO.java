package com.tohome.bundlebundle.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private Integer id;
    private String brand;
    private String name;
    private String thumbnailImg;
    private Integer price;
    private Integer discountRate;
    private String origin;
    private String packageType;
}
