package br.com.relojoaria.service;

import br.com.relojoaria.dto.request.SubServiceRequest;
import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.entity.SubService;

import java.util.List;

public interface SubServiceService {
    SubServiceResponse create(Long serviceOrderId, SubServiceRequest subServiceOrder);
    SubServiceResponse getById(Long id);
    List<SubServiceResponse> getAll();
    void removeSubService(Long serviceOrderId, SubService subService);
}