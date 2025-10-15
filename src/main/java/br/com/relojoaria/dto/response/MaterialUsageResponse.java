package br.com.relojoaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialUsageResponse {
    private Long id;
    private Long stockId;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal quantityUsed;
    private BigDecimal subTotal;
}
