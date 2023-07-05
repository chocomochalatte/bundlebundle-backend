package com.tohome.bundlebundle.order.service;

import com.tohome.bundlebundle.order.mapper.OrderMapper;
import com.tohome.bundlebundle.order.vo.ProductOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCartServiceImpl implements OrderCartService{
    private final OrderMapper orderMapper;

    @Transactional
    public void orderGroupCart(int groupId) {

        //1. 해당 그룹 아이디에 해당하는 그룹 장바구니를 전부 로그 테이블에 저장

        List<ProductOrderVO> ProductOrderVO = orderMapper.createProductList(groupId);
        for (ProductOrderVO productOrder : ProductOrderVO) {
            orderMapper.createLog(productOrder);
        }

        //2. 해당 그룹 아이디에 해당하는 그룹 장바구니를 전부 장바구니 테이블에서 삭제
        Integer resultDelete = orderMapper.deleteOrder(groupId);
        return ;
    }

    @Override
    public List<ProductOrderVO> getLog(Integer memberId) {
        List<ProductOrderVO> logList = orderMapper.getLog(memberId);
        return logList;
    }
}
