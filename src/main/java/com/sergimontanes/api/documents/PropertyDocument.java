package com.sergimontanes.api.documents;

import com.sergimontanes.api.model.PropertyType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PropertyDocument implements Document {

    private String id;
    private String title;
    private String address;
    private PropertyType propertyType;
    private int size;
    private double price;
    private Location location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PropertyRooms rooms;

    @Override
    public String getIndexName() {
        return "properties";
    }

    public String getId() {
        return id;
    }

    @Getter
    @Setter
    public static class Location {
        private double latitude;
        private double longitude;
    }

    @Getter
    @Setter
    public static class PropertyRooms {
        private int bathrooms;
        private int bedrooms;
        private int carparks;
    }
}
