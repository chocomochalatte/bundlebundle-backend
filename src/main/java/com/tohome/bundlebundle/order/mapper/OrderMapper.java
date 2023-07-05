package com.tohome.bundlebundle.order.mapper;

import com.tohome.bundlebundle.order.vo.ProductOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<ProductOrderVO> createProductList(Integer groupId);
    Integer createLog (ProductOrderVO productOrderVO);
    Integer deleteOrder (Integer groupId);
    List<ProductOrderVO> getLog (Integer memberId);
}
