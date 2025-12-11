package br.com.relojoaria.dto.request;

import br.com.relojoaria.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegister {
    @NotBlank
    @Size(min = 3, max = 200)
    private String name;

    @NotBlank
    @Size(min = 3, max = 200)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
