package br.com.relojoaria.dto.request;

import br.com.relojoaria.enums.ServiceStatus;
import br.com.relojoaria.enums.ServiceType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ServiceOrderRequest {
    @NotNull(message = "Requester ID is required")
    private Long clientId;

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    @Size(max = 200, message = "Description must be at most 200 characters")
    private String description;

    @NotNull(message = "Status is required", groups = ServiceStatus.class)
    private ServiceStatus status;

    @NotNull(message = "Type is required",  groups = ServiceType.class)
    private ServiceType type;

    @Digits(integer = 7, fraction = 3, message = "The value must have a maximum of three decimal places")
    private BigDecimal addValue = BigDecimal.ZERO;

    @NotNull(message = "End Date is required")
    private LocalDateTime endDate;

    @Valid
    @NotNull(groups = {MaterialUsageRequest.class}, message = "items are required")
    private List<MaterialUsageRequest> items;

}
