package com.tohome.bundlebundle.product.service;

import com.tohome.bundlebundle.product.vo.ProductVO;

import java.util.List;

public interface ProductService {
    List<ProductVO> showProducts(String sortType);

    ProductVO showProductDetail(Integer productId);
}
