package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.MaterialUsageRequest;
import br.com.relojoaria.dto.response.MaterialUsageResponse;
import br.com.relojoaria.entity.MaterialUsage;
import br.com.relojoaria.entity.Product;
import br.com.relojoaria.entity.ServiceOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialUsageAdapter {

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productPrice", source = "product.price")
    MaterialUsageResponse toResponse(MaterialUsage entity);

    @Mapping(target = "productName", source = "product.name")
    MaterialUsageRequest toRequest(MaterialUsage entity);

    default MaterialUsage toEntity(MaterialUsageRequest dto, ServiceOrder serviceOrder, Product product) {
        MaterialUsage entity = new MaterialUsage();
        entity.setServiceOrder(serviceOrder);
        entity.setProduct(product);
        entity.setQuantityUsed(dto.getQuantityUsed());
        return entity;
    }
}
