package com.victorlevin.StockScheduler.repository;

import com.victorlevin.StockScheduler.domain.StockPrice;
import org.springframework.data.keyvalue.repository.KeyValueRepository;


public interface StockPriceRepository extends KeyValueRepository<StockPrice, String> { }
