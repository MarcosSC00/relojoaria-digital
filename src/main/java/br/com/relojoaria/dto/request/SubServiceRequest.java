package br.com.relojoaria.dto.request;

import br.com.relojoaria.enums.ServiceStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SubServiceRequest {
    @NotBlank(message = "title is required")
    @Size(min = 3, max = 100, message = "invalid title")
    private String title;

    @Size(min = 3, max = 200, message = "invalid description")
    private String description;

    @NotNull(groups =  {ServiceStatus.class})
    private ServiceStatus status;

    @Valid
    @NotNull(groups =  {MaterialUsageRequest.class}, message = "items are required")
    private List<MaterialUsageRequest> items;
}
