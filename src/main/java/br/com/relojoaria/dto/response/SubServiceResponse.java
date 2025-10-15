package br.com.relojoaria.dto.response;

import br.com.relojoaria.enums.ServiceStatus;
import br.com.relojoaria.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubServiceResponse {
    private String title;
    private String description;
    private ServiceStatus status;
    private ServiceType type;
    private List<MaterialUsageResponse> items;

}
