package br.com.relojoaria.controller;

import br.com.relojoaria.dto.request.ServiceOrderRequest;
import br.com.relojoaria.dto.request.ServiceOrderUpdate;
import br.com.relojoaria.dto.request.SubServiceRequest;
import br.com.relojoaria.dto.response.ServiceOrderResponse;
import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.service.ServiceOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceOrderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse> create(@RequestBody @Valid ServiceOrderRequest request) {
        ServiceOrderResponse task = serviceOrderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse> update(@PathVariable Long id, @RequestBody @Valid ServiceOrderUpdate dto) {
        return ResponseEntity.ok(serviceOrderService.update(id, dto));
    }

    @PostMapping("/add-subservice/{serviceOrderId}")
    public ResponseEntity<SubServiceResponse> addItemsToServiceOrder(@PathVariable("serviceOrderId") Long serviceOrderId, @RequestBody @Valid SubServiceRequest dto) {
        return ResponseEntity.ok(serviceOrderService.addSubServiceOrder(serviceOrderId, dto));
    }

    @PostMapping("{serviceOrderId}/remove-subservice/{subServiceId}")
    public ResponseEntity<Void> removeItemToServiceOrder(@PathVariable("serviceOrderId") Long serviceOrderId, @PathVariable("subServiceId") Long subServiceId) {
        serviceOrderService.removeSubServiceOrder(serviceOrderId, subServiceId);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
