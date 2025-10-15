package br.com.relojoaria.service;

import br.com.relojoaria.dto.response.MaterialUsageResponse;

import java.util.List;

public interface MaterialUsageService {
    List<MaterialUsageResponse> getAll();
    MaterialUsageResponse getById(Long id);
}
