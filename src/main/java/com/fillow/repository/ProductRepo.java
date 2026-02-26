package com.fillow.repository;

import com.fillow.domain.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    //userId 기반 유저가 등록한 모든 상품 조회
    List<Product> findByUserId(Long userId);

    //상품명으로 조회
    List<Product> findByProductName(String productName);
}
