package com.fillow.service;

import com.fillow.domain.entity.Cost;
import com.fillow.domain.entity.Simulation;
import com.fillow.domain.enums.cost.CostType;
import com.fillow.repository.CostRepo;
import com.fillow.repository.SimulationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class CostService {

    private final SimulationRepo simulationRepo;
    private final CostRepo costRepo;

    //비용생성
    @Transactional
    public Cost create(Long simulId, CostType costType, String costName, BigDecimal amount) {

        //어떤 시뮬의 비용인지 확인
        Simulation simulation = simulationRepo.findById(simulId)
                .orElseThrow(() -> new IllegalArgumentException("시뮬레이션 없음"));

        //비용 객체 생성
        Cost cost = Cost.builder()
                .simulation(simulation)
                .costType(costType)
                .costName(costName)
                .amount(amount)
                .build();
        return costRepo.save(cost);
    }

    //특정 시뮬의 전체 비용조회
    public List<Cost> findBySimulation(Long simulId) {
        return costRepo.findBySimulationSimulId(simulId);
    }

    //특정 시뮬의 비용 타입별 조회
    public List<Cost> findByCostType(Long simulId, CostType costType) {
        return costRepo.findBySimulationSimulIdAndCostType(simulId, costType);
    }
}
