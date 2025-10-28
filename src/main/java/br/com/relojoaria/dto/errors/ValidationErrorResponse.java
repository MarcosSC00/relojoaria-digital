package br.com.relojoaria.dto.errors;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrorResponse {
    private int status;
    private String error;
    private List<FieldErrorResponse> errors;
    private String path;
    private String timestamp;
}
