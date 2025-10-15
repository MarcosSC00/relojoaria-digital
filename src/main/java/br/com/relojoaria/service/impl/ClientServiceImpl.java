package br.com.relojoaria.service.impl;

import br.com.relojoaria.adapter.ClientAdapter;
import br.com.relojoaria.adapter.ServiceOrderAdapter;
import br.com.relojoaria.dto.request.ClientRequest;
import br.com.relojoaria.dto.response.ClientResponse;
import br.com.relojoaria.dto.response.ServiceOrderResponse;
import br.com.relojoaria.entity.Client;
import br.com.relojoaria.repository.ClientRepository;
import br.com.relojoaria.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ServiceOrderAdapter serviceOrderAdapter;
    private final ClientAdapter  clientAdapter;

    @Override
    public List<ClientResponse> findAll() {
        List<ClientResponse> clients = clientRepository.findAllOrderedById().stream()
                .map(clientAdapter::toResponseDTO)
                .toList();
        if (clients.isEmpty()) {
            throw new NoSuchElementException("Nenhum Cliente encontrado");
        }
        return clients;
    }

    @Override
    public ClientResponse findById(Long id) {
        Client entity = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com id: "+id+"não encontrado"));
        return clientAdapter.toResponseDTO(entity);
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
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com id: "+id+"não encontrado"));
        client = clientRepository.save(client);
        return clientAdapter.toResponseDTO(client);
    }

    @Override
    public List<ServiceOrderResponse> getTasks(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente com id: "+id+"não encontrado"));
        if (client.getServiceOrders() == null) {
            throw new NoSuchElementException("Nenhuma task encontrada");
        }
        return client.getServiceOrders().stream().map(serviceOrderAdapter::toResponseDTO).toList();
    }

    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client com id: "+id+"não encontrado"));
        clientRepository.delete(client);
    }
}
