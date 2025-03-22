package com.sergimontanes.api.transformer;

import com.sergimontanes.api.dto.PropertyDTO;
import com.sergimontanes.api.model.Property;
import com.sergimontanes.api.model.PropertyDetails;

public class PropertyTransformer {

    private PropertyTransformer() {
    }

    public static Property transformProperty(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setTitle(propertyDTO.getTitle());
        property.setDescription(propertyDTO.getDescription());
        property.setAddress(propertyDTO.getAddress());
        property.setPropertyType(propertyDTO.getPropertyType());
        PropertyDetails propertyDetails = new PropertyDetails();
        propertyDetails.setPrice(propertyDTO.getPrice());
        propertyDetails.setBedrooms(propertyDTO.getRooms().getBedrooms());
        propertyDetails.setBedrooms(propertyDTO.getRooms().getBathrooms());
        propertyDetails.setBedrooms(propertyDTO.getRooms().getCarparks());
        property.setDetails(propertyDetails);
        return property;
    }

    public static PropertyDTO transformPropertyDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(String.valueOf(property.getId()));
        propertyDTO.setTitle(property.getTitle());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setPrice(property.getDetails().getPrice());
        propertyDTO.setSize(property.getDetails().getSize());
        propertyDTO.setPropertyType(property.getPropertyType());

        PropertyDTO.PropertyRooms propertyRooms = new PropertyDTO.PropertyRooms();
        propertyRooms.setBedrooms(property.getDetails().getBedrooms());
        propertyRooms.setBathrooms(property.getDetails().getBathrooms());
        propertyRooms.setCarparks(property.getDetails().getCarparks());
        propertyDTO.setRooms(propertyRooms);
        propertyDTO.setTags(property.getAmenities());
        return propertyDTO;
    }
}
