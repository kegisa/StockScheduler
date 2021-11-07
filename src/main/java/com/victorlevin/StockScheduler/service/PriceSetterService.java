package com.victorlevin.StockScheduler.service;

import com.victorlevin.StockScheduler.domain.StockPrice;
import com.victorlevin.StockScheduler.repository.StockPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class PriceSetterService {
    private final StockPriceRepository stockPriceRepository;
    private final TinkoffPriceService tinkoffPriceService;

    public void updatePrices() {
        long startTime = System.currentTimeMillis();

        List<StockPrice> stockPrices = new ArrayList<>();
        stockPriceRepository.findAll().forEach(i -> {
            if(Objects.nonNull(i)) {
                stockPrices.add(i);
            }
        });

        log.info("Time for getting from Redis - {}", System.currentTimeMillis() - startTime);

        if(!stockPrices.isEmpty()) {
            List<StockPrice> sourceTinkoff = stockPrices.stream().filter(s -> s.getSource().equals("TINKOFF")).collect(Collectors.toList());
            List<StockPrice> getFromTinkoff = getFromTinkoff(sourceTinkoff);
            long startSaving = System.currentTimeMillis();
            stockPriceRepository.saveAll(getFromTinkoff);
            log.info("Time for saving to Redis - {}", System.currentTimeMillis() - startSaving);
        }
        log.info("Time for update - {}", System.currentTimeMillis() - startTime);
    }

    private List<StockPrice> getFromTinkoff(List<StockPrice> getFromTinkoff){
        List<String> figies = getFromTinkoff.stream().map(s -> s.getFigi()).collect(Collectors.toList());
        log.info("Get {} prices from Tinkoff - {}", figies.size(), figies);
        long startTinkoff = System.currentTimeMillis();
        List<StockPrice> newPrices = tinkoffPriceService.getPricesByFigies(figies);
        log.info("Time for getting from Tinkoff - {}", System.currentTimeMillis() - startTinkoff);
        return newPrices;
    }
}
