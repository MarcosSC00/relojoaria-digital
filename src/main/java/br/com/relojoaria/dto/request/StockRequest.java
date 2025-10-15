package br.com.relojoaria.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String productName;

    @NotNull
    private BigDecimal quantity;
}
