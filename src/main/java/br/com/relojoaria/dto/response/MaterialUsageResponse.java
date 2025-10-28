package br.com.relojoaria.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialUsageResponse {
    private Long id;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal quantityUsed;
    private BigDecimal subTotal;
}
