-- CREATE USER property_admin WITH PASSWORD 'property_admin';
DO $$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'property_db') THEN
      CREATE DATABASE property_db;
END IF;
END $$;

-- Switch to the correct database
\c property_db;

CREATE TABLE IF NOT EXISTS property (
    id                  BIGSERIAL PRIMARY KEY,
    title               VARCHAR(255)   NOT NULL,
    description         TEXT           NOT NULL,
    address             VARCHAR(255)   NOT NULL,
    property_type       VARCHAR(50)    NOT NULL,
    size                INT,
    price               DECIMAL(10, 2) NOT NULL,
    created_at          TIMESTAMP      NOT NULL,
    updated_at          TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS property_details (
    id          BIGSERIAL PRIMARY KEY,
    property_id BIGINT UNIQUE NOT NULL, -- Foreign key to Property table
    bedrooms    INT,
    bathrooms   INT,
    carparks    INT,
    FOREIGN KEY (property_id) REFERENCES property(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS property_amenities (
    id          BIGSERIAL PRIMARY KEY,
    property_id BIGINT       NOT NULL,
    feature     VARCHAR(100) NOT NULL,
    FOREIGN KEY (property_id) REFERENCES property (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users (
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(50) UNIQUE NOT NULL,
    password   VARCHAR(255)       NOT NULL, -- Store hashed password
    email      VARCHAR(100) UNIQUE NOT NULL,
    role       VARCHAR(20)        NOT NULL, -- Enum: Admin, Agent, User
    created_at TIMESTAMP          NOT NULL
);

CREATE TABLE IF NOT EXISTS property_images (
    id          BIGSERIAL PRIMARY KEY,
    property_id BIGINT NOT NULL,
    image_url   VARCHAR(500) NOT NULL,
    FOREIGN KEY (property_id) REFERENCES property(id) ON DELETE CASCADE,
    UNIQUE(user_id, property_id) -- Prevent duplicate favorites
);

CREATE TABLE IF NOT EXISTS user_favorites (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    property_id BIGINT NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (property_id) REFERENCES property(id) ON DELETE CASCADE,
    UNIQUE(user_id, property_id) -- Prevent duplicate favorites
);

-- Grant privileges to the database user
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO property_admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO property_admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO property_admin;-- Insert into property table
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
