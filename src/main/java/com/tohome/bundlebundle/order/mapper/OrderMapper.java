package com.tohome.bundlebundle.order.mapper;

import com.tohome.bundlebundle.order.vo.GroupCartOrderVO;
import com.tohome.bundlebundle.order.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    Integer createOrder(OrderVO orderVO);

    Integer createLog (GroupCartOrderVO groupCartOrderVO);

    Integer insertProductOrdersBatch(List<GroupCartOrderVO> orders);

    Integer createLogs(List<GroupCartOrderVO> orders);

    Integer deleteOrder (Integer groupId);

    List<GroupCartOrderVO> getLog (Integer memberId);

    List<GroupCartOrderVO> findAllById(Integer orderId);

}
