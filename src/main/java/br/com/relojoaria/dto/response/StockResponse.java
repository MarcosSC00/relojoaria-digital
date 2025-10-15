package br.com.relojoaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    private Long id;
    private String productName;
    private BigDecimal price;
    private BigDecimal currentQuantity;
    private String unit;
}
