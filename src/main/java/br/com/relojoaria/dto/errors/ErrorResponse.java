package br.com.relojoaria.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String error;
    private String path;
    private String timestamp;
}
