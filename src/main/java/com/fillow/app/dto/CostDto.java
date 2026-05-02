package com.fillow.app.dto;

import com.fillow.domain.entity.Cost;
import com.fillow.domain.enums.cost.CostType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class CostDto {

    //비용생성요청
    @Getter
    @NoArgsConstructor
    public static class CostCreateRequest {
        private Long simulId;
        private CostType costType;
        private String costName;
        private BigDecimal amount;
    }

    //비용응답
    @Getter
    @Builder
    public static class CostResponse {
        private Long costId;
        private Long simulId;
        private CostType costType;
        private String costName;
        private BigDecimal amount;

        public static CostResponse from(Cost cost) {
            return CostResponse.builder()
                    .costId(cost.getCostId())
                    .simulId(cost.getSimulation().getSimulId())
                    .costType(cost.getCostType())
                    .costName(cost.getCostName())
                    .amount(cost.getAmount())
                    .build();
        }
    }
}
