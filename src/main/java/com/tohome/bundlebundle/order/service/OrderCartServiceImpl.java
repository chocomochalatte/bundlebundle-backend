package com.tohome.bundlebundle.order.service;

import com.tohome.bundlebundle.cart.mapper.GroupCartMapper;
import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.group.mapper.GroupMapper;
import com.tohome.bundlebundle.member.mapper.MemberMapper;
import com.tohome.bundlebundle.order.mapper.OrderMapper;
import com.tohome.bundlebundle.order.vo.GroupCartOrderVO;
import com.tohome.bundlebundle.order.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCartServiceImpl implements OrderCartService{
    private final OrderMapper orderMapper;
    private final MemberMapper memberMapper;
    private final GroupCartMapper groupCartMapper;
    private final GroupMapper groupMapper;

    @Transactional
    public OrderVO orderGroupCart(Integer memberId) {
        // 1. order 생성하기
        Integer groupId = memberMapper.findGroupIdById(memberId)
                                      .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_GROUP_NOT_FOUND));

        Integer owningGroupId = groupMapper.findGroupIdByGroupOwnerId(memberId);
        if (owningGroupId != groupId) {
            throw new BusinessException(ErrorCode.NOT_A_GROUP_OWNER);
        }

        OrderVO orderVO = new OrderVO(memberId, groupId);
        orderMapper.createOrder(orderVO);
        Integer orderId = orderVO.getId();
        if (orderId == null) {
            throw new BusinessException(ErrorCode.ORDER_CREATE_FAILURE);
        }

        // 2. 해당 그룹 아이디에 해당하는 그룹 장바구니 가져오기
        List<GroupCartOrderVO> groupCartOrders = groupCartMapper.findAllByGroupId(orderVO);

        // 3. 주문 로그를 하나씩 저장
        for (GroupCartOrderVO groupCartOrder : groupCartOrders) {
            groupCartOrder.setOrderId(orderId);
            Integer resultInsert = orderMapper.createLog(groupCartOrder);
            if (resultInsert == 0) {
                throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
            }
        }

        // 4. 해당 그룹 아이디에 해당하는 그룹 장바구니를 전부 장바구니 테이블에서 삭제
        Integer resultDelete = groupCartMapper.deleteAllByGroupId(groupId);
        if (resultDelete == 0) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }

        return orderVO;
    }

    @Override
    public List<GroupCartOrderVO> getLog(Integer memberId) {
        List<GroupCartOrderVO> logList = orderMapper.getLog(memberId);
        return logList;
    }

    @Override
    public List<GroupCartOrderVO> showOrderResult(Integer orderId) {
        List<GroupCartOrderVO> orderResult = orderMapper.findAllById(orderId);
        return orderResult;
    }
}