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
    created_at          TIMESTAMP      NOT NULL,
    updated_at          TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS property_details (
    id              BIGSERIAL PRIMARY KEY,
    property_id     BIGINT UNIQUE NOT NULL, -- Foreign key to Property table
    bedrooms        INT,
    bathrooms       INT,
    carparks        INT,
    property_size   INT,
    price           DECIMAL(10, 2) NOT NULL,
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
    FOREIGN KEY (property_id) REFERENCES property(id) ON DELETE CASCADE
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
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO property_admin;
