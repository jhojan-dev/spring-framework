package com.jhojan.springboot.jparelationship.repositories;

import com.jhojan.springboot.jparelationship.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.addresses WHERE c.id = :id")
    Optional<Client> findOneWithAddresses(Long id);

    @Query("SELECT c FROM Client c LEFT JOIN FETCH c.invoices WHERE c.id = :id")
    Optional<Client> findOneWithInvoices(Long id);

    @Query("SELECT c FROM Client c " +
            "LEFT JOIN FETCH c.invoices " +
            "LEFT JOIN FETCH c.addresses " +
            "WHERE c.id = :id")
    Optional<Client> findOne(Long id);

}
