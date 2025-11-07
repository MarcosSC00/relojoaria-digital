package br.com.relojoaria.repository;

import br.com.relojoaria.entity.ServiceOrder;
import br.com.relojoaria.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    @Query("SELECT s FROM ServiceOrder s ORDER BY s.id ASC")
    List<ServiceOrder> findAllOrderedById();

    @Query("SELECT ss FROM SubService ss where ss.serviceOrder.id = :serviceId")
    List<SubService> getSubServiceOrders(@Param("serviceId") Long serviceId);
}
