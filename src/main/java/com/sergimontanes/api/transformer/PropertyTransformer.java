package com.sergimontanes.api.transformer;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.model.Property;
import com.sergimontanes.api.model.PropertyType;

import java.math.BigDecimal;

public class PropertyTransformer {

    private PropertyTransformer() {
    }

    public static Property transformProperty(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setTitle(propertyDTO.getTitle());
        property.setDescription(propertyDTO.getDescription());
        property.setAddress(propertyDTO.getAddress());

        property.setPropertyType(PropertyType.APARTMENT);
        property.setPrice(BigDecimal.valueOf(1000000.00));

        return property;
    }

    public static PropertyDTO transformPropertyDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(String.valueOf(property.getId()));
        propertyDTO.setTitle(property.getTitle());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setPrice(property.getPrice());
        return propertyDTO;
    }
}
