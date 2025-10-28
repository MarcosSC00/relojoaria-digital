package br.com.relojoaria.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockRequest {

    @NotBlank(message = "product name is required")
    @Size(max = 100, min = 3, message = "invalid product name")
    private String productName;

    @NotNull(message = "quantity is required")
    @Digits(integer = 7, fraction = 3, message = "The value must have a maximum of three decimal places")
    private BigDecimal quantity;
}
