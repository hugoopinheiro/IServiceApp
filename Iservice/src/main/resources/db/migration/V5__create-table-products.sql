CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    seller_id INTEGER,
    category_id INTEGER,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(200),
    price NUMERIC(10, 2) NOT NULL,
    category VARCHAR(45) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_seller FOREIGN KEY (seller_id) REFERENCES seller(seller_id)
);
