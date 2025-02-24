package com.sergimontanes.api.dto;

import com.sergimontanes.api.model.PropertyType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class PropertySearchDTO {
    //private double minPrice;
    //private double maxPrice;
   // private PropertyType propertyType;
    private Integer minBedrooms;
    private Integer minBathrooms;
    private Map<String, Integer> roomFilters;
}
