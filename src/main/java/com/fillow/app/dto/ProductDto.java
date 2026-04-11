package com.fillow.app.dto;

import com.fillow.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

    //상품생성요청
    @Getter
    @NoArgsConstructor
    public static class ProductCreateRequest {
        private Long userId;
        private String productName;
    }

    //상품 응답
    @Getter
    @Builder
    public static class ProductResponse{
        private Long productId;
        private String productName;
        private Long userId;

        public static ProductResponse from (Product product) {
            return ProductResponse.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .userId(product.getUser().getUserId())
                    .build();
        }
    }

}
