package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.ClientRequest;
import br.com.relojoaria.dto.response.ClientResponse;
import br.com.relojoaria.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientAdapter {
    ClientAdapter INSTANCE = Mappers.getMapper(ClientAdapter.class);

    ClientResponse toResponseDTO(Client entity);

    Client toEntity(ClientRequest dto);

}
