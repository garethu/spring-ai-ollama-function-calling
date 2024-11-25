-- Create the table
CREATE TABLE booking (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    travel_date DATE,
    travel_from VARCHAR(50),
    travel_to VARCHAR(50),
    status CHAR(1),
    booking_class VARCHAR(20)
);

-- Insert the data
INSERT INTO booking (id, first_name, last_name, travel_date, travel_from, travel_to, status, booking_class)
VALUES
    (101, 'John', 'Doe', '2024-11-22', 'Amsterdam', 'Utrecht', 'Y', 'BUSINESS'),
    (102, 'Jane', 'Smith', '2024-11-24', 'Rotterdam', 'Utrecht', 'Y', 'BUSINESS'),
    (103, 'Michael', 'Johnson', '2024-11-26', 'Arnhem', 'Amsterdam', 'Y', 'BUSINESS'),
    (104, 'Sarah', 'Williams', '2024-11-28', 'Utrecht', 'Amersfoort', 'Y', 'PREMIUM ECONOMY'),
    (105, 'Robert', 'Taylor', '2024-11-30', 'Zevenaar', 'Groningen', 'Y', 'ECONOMY');


CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS vector_store (
	id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
	content text,
	metadata json,
	embedding vector(1536)
);

CREATE INDEX ON vector_store USING HNSW (embedding vector_cosine_ops);