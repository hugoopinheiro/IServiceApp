CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    user_id INTEGER,
    product_id INTEGER,
    credit_card_id INTEGER,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(250) NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(product_id),
    CONSTRAINT fk_credit_card FOREIGN KEY (credit_card_id) REFERENCES creditCard(credit_card_id)
);
