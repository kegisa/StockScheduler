package com.victorlevin.StockScheduler.service;


import com.victorlevin.StockScheduler.config.ApiConfig;
import com.victorlevin.StockScheduler.domain.StockPrice;
import com.victorlevin.StockScheduler.dto.GetPricesDto;
import com.victorlevin.StockScheduler.dto.StockFigiesPricesDto;
import com.victorlevin.StockScheduler.exception.TinkoffServiceException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TinkoffPriceService {
    private final ApiConfig config;

    private final RestTemplate restTemplate;

    public TinkoffPriceService(RestTemplateBuilder restTemplateBuilder, ApiConfig config) {
        this.config = config;
        this.restTemplate = restTemplateBuilder.build();
    }


    public List<StockPrice> getPricesByFigies(List<String> figies) {
        List<StockPrice> prices = new ArrayList<>();
        String url = config.getTinkoffService() + config.getGetPricesByFigies();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        GetPricesDto dto = new GetPricesDto(figies);
        HttpEntity<GetPricesDto> entity = new HttpEntity<>(dto, headers);
        ResponseEntity<StockFigiesPricesDto> responseEntity
                = this.restTemplate.postForEntity(url, entity, StockFigiesPricesDto.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            responseEntity.getBody().getPrices().forEach(i -> prices.add(new StockPrice(i.getFigi(), i.getPrice(), "TINKOFF")));
            return prices;
        } else {
            throw new TinkoffServiceException(responseEntity.toString());
        }
    }
}
