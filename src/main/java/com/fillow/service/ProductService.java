package com.fillow.service;

import com.fillow.domain.entity.Product;
import com.fillow.domain.entity.User;
import com.fillow.repository.ProductRepo;
import com.fillow.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class ProductService {

    //product, user DB 가져옴
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    //상품생성
    @Transactional
    public Product createProduct(Long userId, String productName) {

        //유저확인
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        //상품 객체 생성
        Product product = Product.builder()
                .user(user)
                .productName(productName)
                .build();
        return productRepo.save(product);
    }

    //userId 기반 특정 유저의 모든 상품 조회
    public List<Product> findByUser(Long userId) {
        return productRepo.findByUserUserId(userId);
    }

    //상품명으로 조회
    public List<Product> findByProductName(String productName) {
        return productRepo.findByProductName(productName);
    }
}
