package com.fillow.domain.entity;

import com.fillow.domain.enums.cost.CostType;
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
@Table(name = "cost")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "cost_id")
    private Long costId;

    // Simulation(1) : cost(N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simul_id", nullable = false)
    private Simulation simulation;

    @Enumerated(EnumType.STRING)
    @Column(name = "cost_type", nullable = false)
    private CostType costType;

    @Column(name = "cost_name", nullable = false)
    private String costName;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
