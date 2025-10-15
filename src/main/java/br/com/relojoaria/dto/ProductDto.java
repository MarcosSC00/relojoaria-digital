package br.com.relojoaria.dto;

import br.com.relojoaria.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private UnitType unit;
    private BigDecimal price;
}
