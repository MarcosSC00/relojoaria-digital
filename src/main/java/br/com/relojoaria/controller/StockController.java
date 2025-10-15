package br.com.relojoaria.controller;

import br.com.relojoaria.dto.request.StockRequest;
import br.com.relojoaria.dto.response.StockResponse;
import br.com.relojoaria.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@Validated
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponse> create(@RequestBody @Valid StockRequest stock) {
        return new ResponseEntity<>(stockService.create(stock), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StockResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(stockService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<StockResponse>> getAll(){
        return ResponseEntity.ok(stockService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
