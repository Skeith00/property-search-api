package com.sergimontanes.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String address;
    @Column(name = "property_type", nullable = false)
    private PropertyType propertyType;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private PropertyDetails details;
    // @Column
    // @OneToMany(mappedBy = "property_amenities", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<String> propertyFeatures;

    // Mapping Property Amenities as a List<String>
    @ElementCollection
    @CollectionTable(name = "property_amenities", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "feature")
    private List<String> amenities;
}
