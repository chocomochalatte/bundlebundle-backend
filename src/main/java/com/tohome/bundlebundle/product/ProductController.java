package com.tohome.bundlebundle.product;

import com.tohome.bundlebundle.product.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> showProducts(@RequestParam("sort") String sortType) {

//        if (sortType.equals("best")) {
//
//        }

        List<ProductVO> result = productService.showProducts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
