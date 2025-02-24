-- Insert into products table
INSERT INTO property (name, price, category)
VALUES ('Luxury Apartment', 450000, 'Real Estate'),
       ('Modern House', 600000, 'Real Estate');

-- Insert into rooms table (linked to product_id)
INSERT INTO room (name, number, property_id)
VALUES ('Bedroom', 3, 1),
       ('Bathroom', 2, 1),
       ('Bedroom', 4, 2),
       ('Bathroom', 3, 2);
