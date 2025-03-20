-- Insert into property table
INSERT INTO property (title, description, address, property_type, size, price, created_at, updated_at)
VALUES ('Modern Family Home', 'A spacious and modern house perfect for families.', '123 Main St, Sydney', 'House', 250,
        750000.00, NOW(), NOW()),
       ('Luxury Apartment', 'A stunning apartment with ocean views.', '45 Beach Rd, Melbourne', 'Apartment', 120,
        950000.00, NOW(), NOW()),
       ('Cozy Townhouse', 'A cozy townhouse in a quiet neighborhood.', '78 Green Ave, Brisbane', 'Townhouse', 180,
        650000.00, NOW(), NULL);

-- Insert into room table
INSERT INTO property_details (bedrooms, bathrooms, carparks, property_id)
VALUES (1, 1, 1, 1),
       (1, 1, 0, 1),
       (NULL, NULL, 0, 1), -- Common areas
       (1, 1, 1, 2),
       (1, 1, 0, 2),
       (NULL, NULL, 0, 2),
       (1, 1, 1, 3),
       (1, 1, 0, 3);

-- Insert into property_amenities table
INSERT INTO property_amenities (property_id, feature)
VALUES (1, 'Swimming Pool'),
       (1, 'Garage'),
       (1, 'Garden'),
       (2, 'Gym'),
       (2, 'Balcony'),
       (2, 'Ocean View'),
       (3, 'Backyard'),
       (3, 'Air Conditioning'),
       (3, 'Security System');
