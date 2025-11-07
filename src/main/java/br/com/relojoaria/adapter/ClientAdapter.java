package br.com.relojoaria.adapter;

import br.com.relojoaria.dto.request.ClientRequest;
import br.com.relojoaria.dto.response.ClientResponse;
import br.com.relojoaria.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientAdapter {
    ClientResponse toResponseDTO(Client entity);
    Client toEntity(ClientRequest dto);
}
