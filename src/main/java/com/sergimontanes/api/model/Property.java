package com.sergimontanes.api.model;

import jakarta.persistence.*;
import lombok.Data;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "property_type", nullable = false)
    private PropertyType propertyType;
    @Column
    private int size;
    @Column(nullable = false)
    private double price;
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    // @Column
    // @OneToMany(mappedBy = "property_amenities", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<String> propertyFeatures;
    @OneToMany(cascade = CascadeType.ALL) // No 'mappedBy' for unidirectional
    @JoinColumn(name = "property_id") // Foreign key stored in 'rooms' table
    private List<Room> rooms;

}
