package com.tohome.bundlebundle.mapper;

import com.tohome.bundlebundle.product.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

//    @Select("select * from products")
    List<ProductVO> showProducts();
}
