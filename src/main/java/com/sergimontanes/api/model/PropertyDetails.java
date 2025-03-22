package com.sergimontanes.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "property_details")
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int bedrooms;
    @Column
    private int bathrooms;
    @Column
    private int carparks;
    @Column(name = "property_size")
    private int size;
    @Column(name="price", precision=9, scale=2, nullable = false)
    private BigDecimal price;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;
}
