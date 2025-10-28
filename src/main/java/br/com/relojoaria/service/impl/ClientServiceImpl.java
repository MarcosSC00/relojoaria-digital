package br.com.relojoaria.service.impl;

import br.com.relojoaria.adapter.ClientAdapter;
import br.com.relojoaria.adapter.ServiceOrderAdapter;
import br.com.relojoaria.dto.ClientCustomDto;
import br.com.relojoaria.dto.request.ClientRequest;
import br.com.relojoaria.dto.response.ClientResponse;
import br.com.relojoaria.dto.response.ServiceOrderResponse;
import br.com.relojoaria.entity.Client;
import br.com.relojoaria.entity.ServiceOrder;
import br.com.relojoaria.repository.ClientRepository;
import br.com.relojoaria.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ServiceOrderAdapter serviceOrderAdapter;
    private final ClientAdapter  clientAdapter;

    @Override
    public List<ClientCustomDto> getAll() {
        List<ClientCustomDto> clients = clientRepository.findAllOrderedById();
        if (clients.isEmpty()) {
            throw new NoSuchElementException("Nenhum cliente encontrado");
        }
        return clients;
    }

    @Override
    public ClientCustomDto getById(Long id) {
        return clientRepository.findClientWithoutOrders(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com id: "+id+"não encontrado"));
    }

    @Override
    public ClientResponse create(ClientRequest dto) {
        Client entity = clientAdapter.toEntity(dto);
        if(clientRepository.existsByName(entity.getName())){
            throw new IllegalArgumentException("Cliente já existente");
        }
        return clientAdapter.toResponseDTO(clientRepository.save(entity));
    }

    @Override
    public ClientResponse update(Long id, ClientRequest dto) {
        if(!clientRepository.existsById(id)){
            throw new IllegalArgumentException("cliente não encontrado");
        }
        Client client = clientRepository.save(clientAdapter.toEntity(dto));
        return clientAdapter.toResponseDTO(client);
    }

    @Override
    public List<ServiceOrderResponse> getTasks(Long id) {
        if(!clientRepository.existsById(id)){
            throw new IllegalArgumentException("cliente não encontrado");
        }
        List<ServiceOrder> orders = clientRepository.findServiceOrdersById(id);
        if (orders == null) {
            throw new NoSuchElementException("Nenhuma task encontrada");
        }
        return orders.stream().map(serviceOrderAdapter::toResponseDTO).toList();
    }

    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client com id: "+id+" não encontrado"));
        clientRepository.delete(client);
    }
}
