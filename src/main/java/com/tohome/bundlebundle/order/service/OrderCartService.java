package com.tohome.bundlebundle.order.service;

import com.tohome.bundlebundle.order.vo.OrderVO;
import com.tohome.bundlebundle.order.vo.GroupCartOrderVO;

import java.util.List;

public interface OrderCartService {

    OrderVO orderGroupCart(Integer memberId);

    List<GroupCartOrderVO> getLog(Integer memberId);

    List<GroupCartOrderVO> showOrderResult(Integer orderId);
}
