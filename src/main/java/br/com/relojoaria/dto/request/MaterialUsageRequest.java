package br.com.relojoaria.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialUsageRequest {
    @NotBlank(message = "Product name is required")
    @Size(max = 100, min = 3, message = "invalid product name")
    private String productName;

    @NotNull(message = "Quantity used is required")
    @Digits(integer = 8, fraction = 2, message = "The quantity must have a maximum of two decimal places")
    private BigDecimal quantityUsed;
}
