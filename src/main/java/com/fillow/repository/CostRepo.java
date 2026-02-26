package com.fillow.repository;

import com.fillow.domain.entity.Cost;
import com.fillow.domain.enums.cost.CostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRepo extends JpaRepository<Cost, Long> {

    //특정시뮬레이션의 모든 비용조회
    List<Cost> findBySimulationSimulId(Long simulId);

    //특정시뮬+비용타입 조회
    List<Cost> findBySimulationSimulIdAndCostType(Long simulId, CostType costType);
}
