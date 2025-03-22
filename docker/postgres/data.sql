-- Insert into property table
INSERT INTO property (title, description, address, property_type, created_at, updated_at)
VALUES ('Modern Family Home', 'A spacious and modern house perfect for families.', '123 Main St, Sydney', 'House', NOW(), NOW()),
       ('Luxury Apartment', 'A stunning apartment with ocean views.', '45 Beach Rd, Melbourne', 'Apartment', NOW(), NOW()),
       ('Cozy Townhouse', 'A cozy townhouse in a quiet neighborhood.', '78 Green Ave, Brisbane', 'Townhouse', NOW(), NOW());

-- Insert into room table
INSERT INTO property_details (property_id, bedrooms, bathrooms, carparks, property_size, price)
VALUES (1, 4, 2, 2, 250, 750000.00),
       (2, 3, 2, 1, 120, 950000.00),
       (3, 3, 1, 1, 180, 650000.00);

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
