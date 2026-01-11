package br.com.relojoaria.dto.response;

import br.com.relojoaria.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String token;
    UserResponse userResponse;
}
