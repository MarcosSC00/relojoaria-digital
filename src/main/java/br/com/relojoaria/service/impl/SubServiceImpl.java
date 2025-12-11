package br.com.relojoaria.service.impl;

import br.com.relojoaria.adapter.SubServiceAdapter;
import br.com.relojoaria.dto.request.SubServiceRequest;
import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.entity.ServiceOrder;
import br.com.relojoaria.entity.Stock;
import br.com.relojoaria.entity.SubService;
import br.com.relojoaria.error.exception.NotFoundException;
import br.com.relojoaria.repository.ServiceOrderRepository;
import br.com.relojoaria.repository.StockRepository;
import br.com.relojoaria.repository.SubServiceRepository;
import br.com.relojoaria.service.SubServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;
    private final ServiceOrderRepository serviceOrderRepository;
    private final StockRepository stockRepository;
    private final SubServiceAdapter adapter;

    @Override
    public SubServiceResponse create(Long serviceOrderId, SubServiceRequest request) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new NotFoundException("Ordem de serviço com id:"+serviceOrderId+" não encontrada"));

        SubService subService = SubService.builder()
                .serviceOrder(serviceOrder)
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .price(request.getPrice())
                .build();

        return priceCalculator(request, serviceOrder, subService);
    }

    private SubServiceResponse priceCalculator(SubServiceRequest request, ServiceOrder serviceOrder, SubService sub) {
        //processStockItems(sub, request.getItems());

//        BigDecimal SubServicesPrice = sub.getItems().stream()
//                .map(MaterialUsage::getSubTotal)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        sub.setPrice(SubServicesPrice);

        serviceOrder.getSubServices().add(sub);

        serviceOrder.setTotalPrice(serviceOrder.getTotalPrice().add(sub.getPrice()));
        serviceOrderRepository.save(serviceOrder);

        return  adapter.toResponse(sub);
    }

    @Override
    public SubServiceResponse getById(Long id) {
        SubService subService = subServiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhum sub-serviço encontrado"));
        return adapter.toResponse(subService);
    }

    @Override
    public List<SubServiceResponse> getAll() {
        List<SubService> subServices = subServiceRepository.findAll();
        if (subServices.isEmpty()) {
            throw new NotFoundException("Nenhum sub-serviço encontrado");
        }
        return subServices.stream().map(adapter::toResponse).toList();
    }

    @Override
    public void removeSubService(Long serviceOrderId, SubService subService) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new NotFoundException("Ordem de serviço com id:"+serviceOrderId+"não encontrada"));
        SubService sub = serviceOrder.getSubServices().stream()
                .filter(s -> s.getId().equals(subService.getId())).findFirst().get();
        serviceOrder.getSubServices().remove(sub);
        serviceOrder.setTotalPrice(serviceOrder.getTotalPrice().subtract(sub.getPrice()));
        serviceOrderRepository.save(serviceOrder);
    }

//    private void processStockItems(SubService subService, List<MaterialUsageRequest> stockItems) {
//        for (MaterialUsageRequest itemRequest : stockItems) {
//            Stock stock = stockRepository.findByProductName(itemRequest.getProductName())
//                    .orElseThrow(() -> new NotFoundException(
//                            "Stock não possui o item: " + itemRequest.getProductName()));
//
//            validateStockQuantity(stock, itemRequest.getQuantityUsed());
//
//            BigDecimal subTotal = stock.getProduct().getPrice().multiply(itemRequest.getQuantityUsed());
//
//            MaterialUsage materialUsage = MaterialUsage.builder()
//                    .subService(subService)
//                    .product(stock.getProduct())
//                    .quantityUsed(itemRequest.getQuantityUsed())
//                    .subTotal(subTotal)
//                    .build();
//
//            subService.getItems().add(materialUsage);
//            updateStockQuantity(stock, itemRequest.getQuantityUsed());
//        }
//    }

    private void validateStockQuantity(Stock stock, BigDecimal quantityUsed) {
        if (stock.getCurrentQuantity().compareTo(quantityUsed) < 0) {
            throw new IllegalArgumentException("Estoque insuficiente para");
        }
    }

    private void updateStockQuantity(Stock stock, BigDecimal quantityUsed) {
        stock.setCurrentQuantity(stock.getCurrentQuantity().subtract(quantityUsed));
        stockRepository.save(stock);
    }
}
