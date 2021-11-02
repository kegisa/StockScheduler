package com.victorlevin.StockScheduler;

import com.victorlevin.StockScheduler.config.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties(ApiConfig.class)
@EnableScheduling
@SpringBootApplication
public class StockSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockSchedulerApplication.class, args);
	}

}
