package br.com.relojoaria.repository;

import br.com.relojoaria.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s JOIN s.product p WHERE p.name = :productName")
    Optional<Stock> findByProductName(@Param("productName") String productName);
    boolean existsByProductName(@Param("productName") String productName);
}
