package com.tohome.bundlebundle.order.mapper;

import com.tohome.bundlebundle.order.vo.OrderVO;
import com.tohome.bundlebundle.order.vo.ProductOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<ProductOrderVO> findAllByGroupId(OrderVO orderVO);

    Integer createOrder(OrderVO orderVO);

    Integer createLog (ProductOrderVO productOrderVO);

    Integer deleteOrder (Integer groupId);

    List<ProductOrderVO> getLog (Integer memberId);

    List<ProductOrderVO> findAllById(Integer orderId);

}
