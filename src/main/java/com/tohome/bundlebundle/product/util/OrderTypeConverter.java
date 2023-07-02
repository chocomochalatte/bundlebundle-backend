package com.tohome.bundlebundle.product.util;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.product.vo.OrderTypeVO;

import java.util.Arrays;

public class OrderTypeConverter {

    public static OrderTypeVO findOrderType(String requestParam) {
        return Arrays.stream(OrderType.values())
                     .filter(orderType -> orderType.getRequestParam().equals(requestParam))
                     .findFirst()
                     .map(orderType -> new OrderTypeVO(orderType.getSortingField(), orderType.getSortingDirection()))
                     .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_ORDER_TYPE));
    }

}
