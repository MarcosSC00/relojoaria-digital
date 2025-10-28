package br.com.relojoaria.service;

import br.com.relojoaria.dto.request.ServiceOrderRequest;
import br.com.relojoaria.dto.request.ServiceOrderUpdate;
import br.com.relojoaria.dto.request.SubServiceRequest;
import br.com.relojoaria.dto.response.ServiceOrderResponse;
import br.com.relojoaria.dto.response.SubServiceResponse;

import java.util.List;

public interface ServiceOrderService {
    List<ServiceOrderResponse> getAll();
    ServiceOrderResponse getById(Long id);
    ServiceOrderResponse create(ServiceOrderRequest dto);
    ServiceOrderResponse update(Long id, ServiceOrderUpdate dto);
    void delete(Long id);
    SubServiceResponse addSubServiceOrder(Long serviceOrderId, SubServiceRequest dto);
    void removeSubServiceOrder(Long serviceOrderId, Long subServiceId);
    List<SubServiceResponse> getSubServiceOrders(Long serviceOrderId);
}
