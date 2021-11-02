package com.victorlevin.StockScheduler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    private String tinkoffService;
    private String getPricesByFigies;
}
