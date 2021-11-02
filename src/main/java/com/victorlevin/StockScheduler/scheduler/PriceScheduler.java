package com.victorlevin.StockScheduler.scheduler;

import com.victorlevin.StockScheduler.service.PriceSetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceScheduler {
    private final PriceSetterService priceSetterService;

    @Scheduled(fixedDelay = 5000)
    public void updatePrices() {
        priceSetterService.updatePrices();
    }
}
