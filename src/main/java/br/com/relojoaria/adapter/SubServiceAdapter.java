package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.SubServiceRequest;
import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.entity.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {MaterialUsageAdapter.class})
public interface SubServiceAdapter {
    SubServiceAdapter INSTANCE = Mappers.getMapper(SubServiceAdapter.class);

    SubServiceResponse toResponse(SubService subServiceOrder);

    @Mapping(target = "type", source = "type")
    SubService toEntity(SubServiceRequest subServiceRequest);

    @Mapping(target = "type", source = "type")
    SubService toEntity(SubServiceResponse subServiceOrderResponse);
}
