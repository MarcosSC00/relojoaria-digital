package br.com.relojoaria.controller;

import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sub-service")
@RequiredArgsConstructor
public class SubServiceController {

    private final SubServiceService service;

    @GetMapping
    ResponseEntity<List<SubServiceResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<SubServiceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
