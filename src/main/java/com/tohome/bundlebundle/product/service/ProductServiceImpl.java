package com.tohome.bundlebundle.product.service;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.product.vo.OrderTypeVO;
import com.tohome.bundlebundle.product.vo.ProductVO;
import com.tohome.bundlebundle.product.mapper.ProductMapper;
import com.tohome.bundlebundle.util.OrderTypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public List<ProductVO> showProducts(String sortType) {
        OrderTypeVO orderTypeVO = OrderTypeConverter.findOrderType(sortType);
        log.info(orderTypeVO.toString());
        List<ProductVO> result = productMapper.findAllProductsByOrderType(orderTypeVO);
        return result;
    }

    @Override
    public ProductVO showProductDetail(Integer productId) {
        ProductVO result = productMapper.findProductById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        return result;
    }

}
