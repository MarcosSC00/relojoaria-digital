package br.com.relojoaria.controller;

import br.com.relojoaria.dto.response.MaterialUsageResponse;
import br.com.relojoaria.service.MaterialUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/material-usage")
@RequiredArgsConstructor
public class MaterialUsageController {

    private final MaterialUsageService service;

    @GetMapping("/{id}")
    public ResponseEntity<MaterialUsageResponse> getByServiceOrderId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<MaterialUsageResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
