package com.sergimontanes.api.dto;

import com.sergimontanes.api.model.PropertyType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PropertyDTO {

    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String address;
    private PropertyType propertyType;
    private int size;
    private Location location;
    private PropertyRooms rooms;
    private List<String> tags;

    @Data
    public static class Location {
        private double latitude;
        private double longitude;
    }

    @Data
    public static class PropertyRooms {
        private int bedrooms;
        private int bathrooms;
        private int carparks;
    }

}
