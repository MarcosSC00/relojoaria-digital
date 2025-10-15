package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.MaterialUsageRequest;
import br.com.relojoaria.dto.response.MaterialUsageResponse;
import br.com.relojoaria.entity.MaterialUsage;
import br.com.relojoaria.entity.ServiceOrder;
import br.com.relojoaria.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MaterialUsageAdapter {
    MaterialUsageAdapter INSTANCE = Mappers.getMapper(MaterialUsageAdapter.class);

    @Mapping(target = "stockId", source = "stock.id")
    @Mapping(target = "productName", source = "stock.product.name")
    @Mapping(target = "productPrice", source = "stock.product.price")
    MaterialUsageResponse toResponse(MaterialUsage entity);

    @Mapping(target = "productName", source = "stock.product.name")
    @Mapping(target = "quantityUsed", source = "quantityUsed")
    MaterialUsageRequest toRequest(MaterialUsage entity);

    default MaterialUsage toEntity(MaterialUsageRequest dto, ServiceOrder serviceOrder, Stock stock) {
        MaterialUsage entity = new MaterialUsage();
        entity.setServiceOrder(serviceOrder);
        entity.setStock(stock);
        entity.setQuantityUsed(dto.getQuantityUsed());
        return entity;
    }
}
