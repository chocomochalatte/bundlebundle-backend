package com.tohome.bundlebundle.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    BEST("best", "ID", "ASC"),      // REDIS 적용 필요 - 우선 ID값으로 정렬
    DATE("newest", "CREATED_AT", "DESC"),
    PRICE_ASC("price-asc", "PRICE", "ASC"),
    PRICE_DESC("price-asc", "PRICE", "DESC"),
    DISCOUNT_RATE("discount", "ID", "ASC");     // REDIS 적용 필요 - 우선 ID값으로 정렬

    private String requestParam;
    private String sortingField;
    private String sortingDirection;
}
