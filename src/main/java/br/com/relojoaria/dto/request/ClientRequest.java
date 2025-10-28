package br.com.relojoaria.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must be at most 200 and at least 3 characters")
    private String name;

    @Size(max = 20, message = "Phone number must be at most 20 characters")
    private String phone;
}
