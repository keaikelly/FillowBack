package com.fillow.app.controller;

import com.fillow.app.dto.ProductDto;
import com.fillow.domain.entity.Product;
import com.fillow.service.ProductService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;

    //상품 생성 요청
    @PostMapping("/create")
    public ProductDto.ProductResponse create(
            @RequestBody ProductDto.ProductCreateRequest request
    ){
        Product product=productService.createProduct(
                request.getUserId(),
                request.getProductName()
        );
        return ProductDto.ProductResponse.from(product);
    }

    //특정유저의 상품목록 조회
    @GetMapping("/user/{userId}")
    public List<ProductDto.ProductResponse> findByUser(
            @PathVariable Long userId
    ) {
        return productService.findByUser(userId)
                .stream()
                .map(ProductDto.ProductResponse::from)
                .collect(Collectors.toList());
    }

}
