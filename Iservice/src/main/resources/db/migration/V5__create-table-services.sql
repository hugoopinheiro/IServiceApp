CREATE TABLE services (
    service_id SERIAL PRIMARY KEY,
    provider_id INTEGER,
    category_id INTEGER,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(200),
    price NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_provider FOREIGN KEY (provider_id) REFERENCES seller(seller_id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
