package com.tohome.bundlebundle.product.service;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.product.vo.ProductVO;
import com.tohome.bundlebundle.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    private static final Map<String, Function<ProductServiceImpl, List<ProductVO>>> SORT_METHODS = new HashMap<>();

    static {
        SORT_METHODS.put("best", ProductServiceImpl::findAllProductsByPopularity);
        SORT_METHODS.put("newest", ProductServiceImpl::findAllProductsByLatest);
        SORT_METHODS.put("price-asc", ProductServiceImpl::findAllProductsByPriceAsc);
        SORT_METHODS.put("price-desc", ProductServiceImpl::findAllProductsByPriceDesc);
        SORT_METHODS.put("discount", ProductServiceImpl::findAllProductsByDiscount);
    }

    @Override
    public List<ProductVO> showProducts(String sortType) {
        Function<ProductServiceImpl, List<ProductVO>> sortMethod = Optional.ofNullable(SORT_METHODS.get(sortType))
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));
        return sortMethod.apply(this);
    }

    @Override
    public ProductVO showProductDetail(Integer productId) {
        ProductVO result = productMapper.findProductById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        return result;
    }

    private List<ProductVO> findAllProductsByPopularity() {
        List<ProductVO> result = productMapper.findAllProductsByPopularity();
        return result;
    }

    private List<ProductVO> findAllProductsByLatest() {
        List<ProductVO> result = productMapper.findAllProductsByLatest();
        return result;
    }

    private List<ProductVO> findAllProductsByPriceAsc() {
        List<ProductVO> result = productMapper.findAllProductsPriceAsc();
        return result;
    }

    private List<ProductVO> findAllProductsByPriceDesc() {
        List<ProductVO> result = productMapper.findAllProductsByPriceDesc();
        return result;
    }

    private List<ProductVO> findAllProductsByDiscount() {
        List<ProductVO> result = productMapper.findAllProductsByDiscount();
        return result;
    }

}
