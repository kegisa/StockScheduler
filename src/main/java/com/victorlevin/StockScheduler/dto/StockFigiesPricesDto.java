package com.victorlevin.StockScheduler.dto;


import com.victorlevin.StockScheduler.domain.StockPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockFigiesPricesDto {
    private List<StockPrice> prices;
}
