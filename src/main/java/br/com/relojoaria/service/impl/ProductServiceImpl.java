package br.com.relojoaria.service.impl;

import br.com.relojoaria.adapter.ProductAdapter;
import br.com.relojoaria.dto.ProductDto;
import br.com.relojoaria.entity.Product;
import br.com.relojoaria.repository.ProductRepository;
import br.com.relojoaria.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product entity = productAdapter.toEntity(productDto);
        if(productRepository.existsByName(entity.getName())) {
            throw new IllegalArgumentException("Produto: "+"'" + entity.getName()+ "'" + " já existente");
        }
        Product product = productRepository.save(productAdapter.toEntity(productDto));
        return  productAdapter.toDto(product);
    }

    @Override
    public ProductDto update(String productName, ProductDto productDto) {
        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        product.setName(productDto.getName());
        product.setUnit(productDto.getUnit());
        product.setPrice(productDto.getPrice());

        productRepository.save(product);
        return productAdapter.toDto(product);
    }

    @Override
    public ProductDto getByName(String productName) {
        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        return productAdapter.toDto(product);
    }

    @Override
    public ProductDto getById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        return productAdapter.toDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) {
            throw new NoSuchElementException("Nenhum produto cadastrado");
        }
        return products.stream()
                .map(productAdapter::toDto).toList();
    }

    @Override
    public void delete(String productName) {
        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));
        productRepository.delete(product);
    }
}
