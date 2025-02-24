package com.sergimontanes.api.repository;

import com.sergimontanes.api.dto.PropertySearchDTO;
import com.sergimontanes.api.model.Property;
import com.sergimontanes.api.model.Room;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class PropertySpecification {

        private PropertySpecification() {}
        public static Specification<Property> filterBy(PropertySearchDTO filter) {
            return (Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                Predicate predicate = cb.conjunction();

                /* if (filter.getName() != null && !filter.getName().isEmpty()) {
                    predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
                } */

                if (filter.getMinBathrooms() != null) {
                    predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("bathrooms"), filter.getMinBathrooms()));
                }

                if (filter.getMinBedrooms() != null) {
                    predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("bedrooms"), filter.getMinBedrooms()));
                }

                // Join with Room table and apply filters
                if (filter.getRoomFilters() != null && !filter.getRoomFilters().isEmpty()) {
                    Join<Property, Room> roomJoin = root.join("rooms", JoinType.INNER); // Inner join with Room

                    for (Map.Entry<String, Integer> entry : filter.getRoomFilters().entrySet()) {
                        String roomName = entry.getKey();
                        Integer roomNumber = entry.getValue();

                        Predicate roomPredicate = cb.and(
                                cb.equal(roomJoin.get("name"), roomName),
                                cb.equal(roomJoin.get("number"), roomNumber)
                        );

                        predicate = cb.and(predicate, roomPredicate);
                    }
                }

                return predicate;
            };
    }
}
