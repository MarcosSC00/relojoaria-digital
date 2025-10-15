package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.StockRequest;
import br.com.relojoaria.dto.response.StockResponse;
import br.com.relojoaria.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StockAdapter {

    StockAdapter INSTANCE = Mappers.getMapper(StockAdapter.class);

    Stock toEntity(StockRequest dto);

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "unit", source = "product.unit")
    StockResponse toResponse(Stock stock);

}
