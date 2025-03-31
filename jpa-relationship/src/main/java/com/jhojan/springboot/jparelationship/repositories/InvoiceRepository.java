package com.jhojan.springboot.jparelationship.repositories;

import com.jhojan.springboot.jparelationship.entities.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {



}
