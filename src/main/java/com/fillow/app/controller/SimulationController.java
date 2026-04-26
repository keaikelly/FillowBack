package com.fillow.app.controller;

import com.fillow.app.dto.SimulationDto;
import com.fillow.domain.entity.Simulation;
import com.fillow.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/simulations")
public class SimulationController {

    private final SimulationService simulationService;

    //시뮬생성
    @PostMapping("/create")
    public SimulationDto.SimulationResponse create(
            @RequestBody SimulationDto.SimulationCreateRequest request
            ){
        Simulation simulation=simulationService.create(
                request.getProductId(),
                request.getSimulName(),
                request.getSellPrice(),
                request.getChannel(),
                request.getPlatform(),
                request.getTax(),
                request.getCommision(),
                request.getLocation(),
                request.getDeposit(),
                request.getRent(),
                request.getMarginRate(),
                request.getMonthlyProfit(),
                request.getBreakeven()
        );

        return SimulationDto.SimulationResponse.from(simulation);
    }

    //특정 상품의 시뮬 목록
    @GetMapping("/product/{productId}")
    public List<SimulationDto.SimulationResponse> findByProduct(
            @PathVariable Long productId
    ){
        return simulationService.findByProduct(productId)
                .stream()
                .map(SimulationDto.SimulationResponse::from)
                .toList();
    }

    // 특정 상품의 시뮬레이션 마진율 기준 정렬 조회
    @GetMapping("/product/{productId}/margin")
    public List<SimulationDto.SimulationResponse> findByProductOrderByMarginRate(
            @PathVariable Long productId
    ){
        return simulationService.findByProductOrderByMarginRate(productId)
                .stream()
                .map(SimulationDto.SimulationResponse::from)
                .toList();
    }


    //시뮬레이션 단건조회
    @GetMapping("/{simulId}")
    public SimulationDto.SimulationResponse findById(@PathVariable Long simulId){
        Simulation simulation=simulationService.findById(simulId);
        return SimulationDto.SimulationResponse.from(simulation);
    }

}
