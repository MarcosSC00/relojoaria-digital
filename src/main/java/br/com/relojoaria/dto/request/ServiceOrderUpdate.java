package br.com.relojoaria.dto.request;

import br.com.relojoaria.enums.ServiceStatus;
import br.com.relojoaria.enums.ServiceType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ServiceOrderUpdate {
    private Long requesterId;

    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    @Size(max = 200, message = "Description must be at most 200 characters")
    private String description;

    private ServiceStatus status;

    private ServiceType type;

    @Digits(integer = 7, fraction = 3, message = "The value must have a maximum of three decimal places")
    private BigDecimal addValue = BigDecimal.ZERO;

    private LocalDateTime endDate;

}
