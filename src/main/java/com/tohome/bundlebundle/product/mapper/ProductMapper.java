package com.tohome.bundlebundle.product.mapper;

import com.tohome.bundlebundle.product.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {

    List<ProductVO> findAllProductsByPopularity();

    List<ProductVO> findAllProductsByLatest();

    List<ProductVO> findAllProductsPriceAsc();

    List<ProductVO> findAllProductsByPriceDesc();

    List<ProductVO> findAllProductsByDiscount();

    Optional<ProductVO> findProductById(Integer productId);
}
