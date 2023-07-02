package com.tohome.bundlebundle.product.controller;

import com.tohome.bundlebundle.product.service.ProductService;
import com.tohome.bundlebundle.product.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> showProducts(@RequestParam("sort") String sortType) {

        List<ProductVO> result = productService.showProducts(sortType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> showProductDetail(@PathVariable Integer productId) {

        ProductVO result = productService.showProductDetail(productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
