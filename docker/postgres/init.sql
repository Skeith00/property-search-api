-- CREATE USER property_admin WITH PASSWORD 'property_admin';
CREATE DATABASE property_db;

-- Switch to the correct database
\c property_db;

CREATE TABLE property
(
    id            BIGSERIAL PRIMARY KEY,
    title         VARCHAR(255)   NOT NULL,
    description   TEXT           NOT NULL,
    address       VARCHAR(255)   NOT NULL,
    property_type VARCHAR(50)    NOT NULL,
    size          INT,
    price         DECIMAL(10, 2) NOT NULL,
    created_at    TIMESTAMP      NOT NULL,
    updated_at    TIMESTAMP NULL
);

CREATE TABLE room
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    number      INT          NOT NULL,
    property_id BIGINT       NOT NULL,
    FOREIGN KEY (property_id) REFERENCES property (id) ON DELETE CASCADE
);

CREATE TABLE property_amenities
(
    id          BIGSERIAL PRIMARY KEY,
    property_id BIGINT       NOT NULL,
    feature     VARCHAR(100) NOT NULL,
    FOREIGN KEY (property_id) REFERENCES property (id) ON DELETE CASCADE
);

-- Grant privileges to the database user
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO property_admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO property_admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO property_admin;