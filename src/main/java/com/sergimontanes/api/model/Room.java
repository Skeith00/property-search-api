package com.sergimontanes.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Bedroom, Bathroom, etc.
    private int number; // 1, 2, 3, etc.
    // Getters & Setters
}
