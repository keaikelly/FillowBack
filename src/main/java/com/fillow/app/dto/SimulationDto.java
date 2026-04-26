package com.fillow.app.dto;

import com.fillow.domain.entity.Simulation;
import com.fillow.domain.enums.simulation.Channel;
import com.fillow.domain.enums.simulation.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class SimulationDto {

    @Getter
    @NoArgsConstructor
    public static class SimulationCreateRequest {
        private Long productId;
        private String simulName;
        private Long sellPrice;
        private Channel channel;
        private String platform;
        private BigDecimal tax;
        private BigDecimal commision;
        private Location location;
        private Long deposit;
        private Long rent;
        private Long marginRate;
        private Long monthlyProfit;
        private Long breakeven;
    }

    @Getter
    @Builder
    public static class SimulationResponse {
        private Long simulId;
        private Long productId;
        private String simulName;
        private Long sellPrice;
        private Channel channel;
        private String platform;
        private BigDecimal tax;
        private BigDecimal commision;
        private Location location;
        private Long deposit;
        private Long rent;
        private Long marginRate;
        private Long monthlyProfit;
        private Long breakeven;

        public static SimulationResponse from(Simulation simulation) {
            return SimulationResponse.builder()
                    .simulId(simulation.getSimulId())
                    .productId(simulation.getProduct().getProductId())
                    .simulName(simulation.getSimulName())
                    .sellPrice(simulation.getSellPrice())
                    .channel(simulation.getChannel())
                    .platform(simulation.getPlatform())
                    .tax(simulation.getTax())
                    .commision(simulation.getCommission())
                    .location(simulation.getLocation())
                    .deposit(simulation.getDeposit())
                    .rent(simulation.getRent())
                    .marginRate(simulation.getMarginRate())
                    .monthlyProfit(simulation.getMonthlyProfit())
                    .breakeven(simulation.getBreakeven())
                    .build();
        }
    }
}
