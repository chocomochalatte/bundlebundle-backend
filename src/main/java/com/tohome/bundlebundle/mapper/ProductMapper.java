package com.tohome.bundlebundle.mapper;

import com.tohome.bundlebundle.product.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductVO> findAllProductsByPopularity();

    List<ProductVO> findAllProductsByLatest();

    List<ProductVO> findAllProductsPriceAsc();

    List<ProductVO> findAllProductsByPriceDesc();

    List<ProductVO> findAllProductsByDiscount();
}