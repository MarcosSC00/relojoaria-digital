package br.com.relojoaria.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class StockResponse {
    private Long id;
    private String productName;
    private BigDecimal price;
    private BigDecimal currentQuantity;
    private String unit;
}
