package com.fillow.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder //객체 안전하게 생성
@AllArgsConstructor //모든필드의생성자 자동생성
@NoArgsConstructor //기본생성자 자동생성
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "product_id")
    private Long productId;

    //User의 user_id fk로
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;
}