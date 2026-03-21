package com.fillow.service;

import com.fillow.domain.entity.Product;
import com.fillow.domain.entity.Simulation;
import com.fillow.domain.enums.simulation.Channel;
import com.fillow.domain.enums.simulation.Location;
import com.fillow.repository.ProductRepo;
import com.fillow.repository.SimulationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class SimulationService {

    //db 가져오기
    private final SimulationRepo simulationRepo;
    private final ProductRepo productRepo;

    //시뮬생성
    @Transactional
    public Simulation create(Long productId,
                             String simulName,
                             Long sellPrice,
                             Channel channel,
                             String platform,
                             BigDecimal tax,
                             BigDecimal commission,
                             Location location,
                             Long deposit,
                             Long rent,
                             Long marginRate,
                             Long monthlyProfit,
                             Long breakeven) {

        //product와 엮기 위해 product 존재 확인
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        //시뮬 객체 생성
        Simulation simulation = Simulation.builder()
                .product(product)
                .simulName(simulName)
                .sellPrice(sellPrice)
                .channel(channel)
                .platform(platform)
                .tax(tax)
                .commission(commission)
                .location(location)
                .deposit(deposit)
                .rent(rent)
                .marginRate(marginRate)
                .monthlyProfit(monthlyProfit)
                .breakeven(breakeven)
                .build();
        return simulationRepo.save(simulation);
    }

    // 시뮬레이션 단건 조회
    public Simulation findById(Long simulId) {
        return simulationRepo.findById(simulId)
                .orElseThrow(() -> new IllegalArgumentException("시뮬레이션 없음"));
    }

    // 특정 상품의 시뮬레이션 전체 조회
    public List<Simulation> findByProduct(Long productId) {
        return simulationRepo.findByProductProductId(productId);
    }

    // 특정 상품의 시뮬레이션 마진율 기준 정렬 조회
    public List<Simulation> findByProductOrderByMarginRate(Long productId) {
        return simulationRepo.findByProductProductIdOrderByMarginRateDesc(productId);
    }


}
