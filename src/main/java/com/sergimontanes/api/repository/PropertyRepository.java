package com.sergimontanes.api.repository;

import com.sergimontanes.api.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {
    //https://www.baeldung.com/jpa-and-or-criteria-predicates
}
