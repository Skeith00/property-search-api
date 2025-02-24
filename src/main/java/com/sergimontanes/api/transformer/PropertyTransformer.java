package com.sergimontanes.api.transformer;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.model.Property;

public class PropertyTransformer {

    private PropertyTransformer() {
    }

    public static Property transformProperty(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setTitle(propertyDTO.getTitle());
        property.setAddress(propertyDTO.getAddress());
        property.setTitle(propertyDTO.getTitle());
        property.setTitle(propertyDTO.getTitle());
        return property;
    }

    public static PropertyDTO transformPropertyDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(String.valueOf(property.getId()));
        propertyDTO.setTitle(property.getTitle());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setTitle(property.getTitle());
        propertyDTO.setTitle(property.getTitle());
        return propertyDTO;
    }
}
