package br.com.relojoaria.dto;

import java.time.LocalDateTime;

public interface ClientCustomDto {
    Long getId();
    String getName();
    String getPhone();
    LocalDateTime getCreatedAt();
}
