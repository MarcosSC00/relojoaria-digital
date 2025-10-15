package br.com.relojoaria.repository;

import br.com.relojoaria.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByName(String name);
    @Query("SELECT c FROM Client c ORDER BY c.id ASC")
    List<Client> findAllOrderedById();
}
