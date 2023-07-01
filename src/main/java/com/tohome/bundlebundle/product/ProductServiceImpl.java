package com.tohome.bundlebundle.product;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.tohome.bundlebundle.product.mapper.ProductMapper;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public List<ProductVO> showProducts() {
        List<ProductVO> result = productMapper.showProducts();
        log.info(result);
        log.debug(result);
        return result;
    }
}