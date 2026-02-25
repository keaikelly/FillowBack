package com.fillow.domain.entity;

import com.fillow.domain.enums.simulation.Channel;
import com.fillow.domain.enums.simulation.Location;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder //객체 안전하게 생성
@AllArgsConstructor //모든필드의생성자 자동생성
@NoArgsConstructor //기본생성자 자동생성
@Table(name = "simulation")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "simul_id")
    private Long simulId;

    // Product(1) : Simul(N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "simul_name", nullable = false)
    private String simulName;

    @Column(name = "sell_price", nullable = false)
    private Long sellPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private Channel channel;

    @Column(name = "platform", length = 255)
    private String platform;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "commission")
    private BigDecimal commission;

    @Enumerated(EnumType.STRING)
    @Column(name = "location")
    private Location location;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "rent")
    private Long rent;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


}