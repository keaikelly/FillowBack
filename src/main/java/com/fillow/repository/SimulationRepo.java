package com.fillow.repository;

import com.fillow.domain.entity.Simulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationRepo extends JpaRepository<Simulation, Long> {

    // UserId 기반 유저가 만든 모든 시뮬 조회 (fk 따라감)
    Page<Simulation> findByProductUserUserId(Long userId, Pageable pageable);

    // productId 기반 상품에 대한 모든 시뮬 조회
    List<Simulation> findByProductProductId(Long productId);

    // 마진율기준정렬 (시뮬분석용)
    List<Simulation> findByProductProductIdOrderByMarginRateDesc(Long productId);
}
