package br.com.relojoaria.repository;

import br.com.relojoaria.dto.response.SubServiceResponse;
import br.com.relojoaria.entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    @Query("SELECT s FROM ServiceOrder s ORDER BY s.id ASC")
    List<ServiceOrder> findAllOrderedById();

    @Query(value = "SELECT sso FROM service_order so JOIN sub_service sso on so.id = :seviceId", nativeQuery = true)
    List<SubServiceResponse> getSubServiceOrders(@Param("serviceId") Long serviceId);
}
