package com.tohome.bundlebundle.product;

import com.tohome.bundlebundle.exception.BusinessException;
import com.tohome.bundlebundle.exception.ErrorCode;
import com.tohome.bundlebundle.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Function<ProductServiceImpl, List<ProductVO>> sortMethod = SORT_METHODS.get(sortType);
        if (sortMethod == null) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        return sortMethod.apply(this);
    }

//    @Override
//    public List<ProductVO> showProducts(String sortType) {
//        List<ProductVO> result;
//        switch (sortType) {
//            case "best": result = findAllProductsByPopularity();
//                break;
//            case "newest": result = findAllProductsByLatest();
//                break;
//            case "price-asc": result = findAllProductsByPriceAsc();
//                break;
//            case "price-desc": result = findAllProductsByPriceDesc();
//                break;
//            case "discount":
//                result = findAllProductsByDiscount();
//                break;
//            default:
//                throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
//        }
//        return result;
//    }

    private List<ProductVO> findAllProductsByPopularity() {
        List<ProductVO> result = productMapper.findAllProductsByPopularity();
        log.info(result);
        return result;
    }

    private List<ProductVO> findAllProductsByLatest() {
        List<ProductVO> result = productMapper.findAllProductsByLatest();
        log.info(result);
        return result;
    }

    private List<ProductVO> findAllProductsByPriceAsc() {
        List<ProductVO> result = productMapper.findAllProductsPriceAsc();
        log.info(result);
        return result;
    }

    private List<ProductVO> findAllProductsByPriceDesc() {
        List<ProductVO> result = productMapper.findAllProductsByPriceDesc();
        log.info(result);
        return result;
    }

    private List<ProductVO> findAllProductsByDiscount() {
        List<ProductVO> result = productMapper.findAllProductsByDiscount();
        log.info(result);
        return result;
    }

}
