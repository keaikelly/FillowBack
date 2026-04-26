package com.fillow.app.controller;

import com.fillow.app.dto.CostDto;
import com.fillow.domain.entity.Cost;
import com.fillow.service.CostService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/costs")
public class CostController {
    private final CostService costService;

    //비용생성
    @PostMapping("/create")
    public CostDto.CostResponse create(@RequestBody CostDto.CostCreateRequest request) {
        Cost cost=costService.create(
                request.getSimulId(),
                request.getCostType(),
                request.getCostName(),
                request.getAmount()
        );
        return CostDto.CostResponse.from(cost);
    }

    //특정 시뮬의 전체비용
    @GetMapping("/simulation/{simulId}")
    public List<CostDto.CostResponse> findBySimulation(@PathVariable Long simulId) {
        return costService.findBySimulation(simulId)
                .stream()
                .map(CostDto.CostResponse::from)
                .toList();
    }

    //특정 시뮬의 비용타입별 조회
    @GetMapping("/simulation/{simulId}/type/{costType}")
    public List<CostDto.CostResponse> findByCostType(
            @PathVariable Long simulId,
            @PathVariable String costType
    ) {
        return costService.findByCostType(
                simulId,
                com.fillow.domain.enums.cost.CostType.valueOf(costType.toUpperCase())
        )//enum타입 리턴을 위한 변환
                .stream()
                .map(CostDto.CostResponse::from)
                .toList();
    }
}
