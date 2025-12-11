package br.com.relojoaria.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockRequest {

    @NotBlank(message = "product name is required")
    @Size(max = 100, min = 3, message = "invalid product name")
    private String productName;

    @NotNull(message = "quantity is required")
    @Digits(integer = 8, fraction = 2, message = "The value must have a maximum of three decimal places")
    @DecimalMin(value = "0.01", message = "the value must be greater than 0.00")
    private BigDecimal quantity;
}
