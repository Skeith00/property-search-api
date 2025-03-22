package com.sergimontanes.api.model;

import jakarta.persistence.*;
import lombok.Data;

/*@Entity
@Table(name = "property_amenities")
@Data*/
public class PropertyAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String feature;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
}
