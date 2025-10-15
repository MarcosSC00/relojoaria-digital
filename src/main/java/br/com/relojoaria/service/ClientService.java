package br.com.relojoaria.service;

import br.com.relojoaria.dto.request.ClientRequest;
import br.com.relojoaria.dto.response.ClientResponse;
import br.com.relojoaria.dto.response.ServiceOrderResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> findAll();

    ClientResponse findById(Long id);

    ClientResponse create(ClientRequest dto);

    ClientResponse update(Long id, ClientRequest dto);

    List<ServiceOrderResponse> getTasks(Long id);

    void delete(Long id);

}
