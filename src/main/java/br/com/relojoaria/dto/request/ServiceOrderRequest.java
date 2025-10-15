package br.com.relojoaria.dto.request;

import br.com.relojoaria.enums.ServiceStatus;
import br.com.relojoaria.enums.ServiceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrderRequest {
    @NotNull(message = "Requester ID is required")
    private Long clientId;

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    @Size(max = 200, message = "Description must be at most 200 characters")
    private String description;

    @NotNull(message = "Status is required")
    private ServiceStatus status;

    @NotNull(message = "Type is required")
    private ServiceType type;

    private BigDecimal addValue = BigDecimal.ZERO;

    @NotNull(message = "End Date is required")
    private LocalDateTime endDate;

    @Valid
    @JsonProperty("items")
    private List<MaterialUsageRequest> items;

}
