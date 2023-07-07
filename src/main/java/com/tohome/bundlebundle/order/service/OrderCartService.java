package com.tohome.bundlebundle.order.service;

import com.tohome.bundlebundle.order.vo.OrderVO;
import com.tohome.bundlebundle.order.vo.ProductOrderVO;

import java.util.List;

public interface OrderCartService {

    OrderVO orderGroupCart(OrderVO requestVO);

    List<ProductOrderVO> getLog(Integer memberId);

    List<ProductOrderVO> showOrderResult(Integer orderId);
}
