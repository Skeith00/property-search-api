package com.sergimontanes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum PropertyType {
    HOUSE("House"),
    APARTMENT("Apartment"),
    TOWNHOUSE("Townhouse");

    private final String value;

    PropertyType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    // Convert String to PropertyType
    @Converter(autoApply = true)
    public static class PropertyTypeConverter implements AttributeConverter<PropertyType, String> {

        @Override
        public String convertToDatabaseColumn(PropertyType attribute) {
            return attribute == null ? null : attribute.getValue();
        }

        @Override
        public PropertyType convertToEntityAttribute(String dbData) {
            if (dbData == null) {
                return null;
            }
            for (PropertyType type : PropertyType.values()) {
                if (type.getValue().equalsIgnoreCase(dbData)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown database value: " + dbData);
        }
    }
}
