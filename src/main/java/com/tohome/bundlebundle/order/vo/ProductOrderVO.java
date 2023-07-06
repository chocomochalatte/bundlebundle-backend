package com.tohome.bundlebundle.order.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductOrderVO {
    private Integer productId;
    private Integer memberId;
    private Integer groupId;
    private Integer price;
    private String thumbnailImg;
    private Timestamp createdAt;
    private String name;
    private Integer productCnt;
    private Integer orderId;
}
