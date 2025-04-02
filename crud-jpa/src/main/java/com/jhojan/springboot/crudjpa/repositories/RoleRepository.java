package com.jhojan.springboot.crudjpa.repositories;

import com.jhojan.springboot.crudjpa.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
