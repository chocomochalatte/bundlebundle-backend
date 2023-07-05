package com.tohome.bundlebundle.order.service;

import com.tohome.bundlebundle.order.vo.ProductOrderVO;

import java.util.List;

public interface OrderCartService {

    void orderGroupCart(int groupId);

    List<ProductOrderVO> getLog(Integer memberId);
}
