package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.SubServiceRequest;
import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.entity.SubService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MaterialUsageAdapter.class})
public interface SubServiceAdapter {

    SubServiceResponse toResponse(SubService subServiceOrder);
    SubService toEntity(SubServiceRequest subServiceRequest);
    SubService toEntity(SubServiceResponse subServiceOrderResponse);
}
