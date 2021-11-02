package com.victorlevin.StockScheduler.service;

import com.victorlevin.StockScheduler.domain.StockPrice;
import com.victorlevin.StockScheduler.repository.StockPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class PriceSetterService {
    private final StockPriceRepository stockPriceRepository;
    private final TinkoffPriceService tinkoffPriceService;

    public void updatePrices() {
        long startTime = System.currentTimeMillis();

        List<String> figies = new ArrayList<>();
        stockPriceRepository.findAll().forEach(i -> {
            if(Objects.nonNull(i)) {
                figies.add(i.getFigi());
            }
        });
        log.info("Updating {}", figies);
        log.info("Time for getting from Redis - {}", System.currentTimeMillis() - startTime);
        if(!figies.isEmpty()) {
            long startTinkoff = System.currentTimeMillis();
            List<StockPrice> newPrices = tinkoffPriceService.getPricesByFigies(figies);
            log.info("Time for getting from Tinkoff - {}", System.currentTimeMillis() - startTinkoff);
            long startSaving = System.currentTimeMillis();
            stockPriceRepository.saveAll(newPrices);
            log.info("Time for saving to Redis - {}", System.currentTimeMillis() - startSaving);
        }
        log.info("Time for update - {}", System.currentTimeMillis() - startTime);
    }
}
