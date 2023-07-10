package com.tohome.bundlebundle.cart.mapper;

import com.tohome.bundlebundle.order.vo.OrderVO;
import com.tohome.bundlebundle.order.vo.GroupCartOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupCartMapper {

	List<GroupCartOrderVO> findAllByGroupId(OrderVO orderVO);

	Integer deleteAllByGroupId(Integer groupId);

}
