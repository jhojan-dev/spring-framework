package com.jhojan.springboot.jparelationship.repositories;

import com.jhojan.springboot.jparelationship.entities.ClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {
}
