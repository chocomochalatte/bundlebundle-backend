package com.tohome.bundlebundle.order.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data

public class ProductOrderVO {
    private Integer productId;
    private Integer memberId;
    private Integer groupId;
    private Integer price;
    private String ThumbnailImg;
    private  String brand;
    private Timestamp createdAt;
    private  Integer categoryId;
    private  String name;
    private  String origin;
    private String packageType;
    private Integer productCnt;
}
