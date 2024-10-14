CREATE TABLE reviews (
    review_id SERIAL PRIMARY KEY,
    user_id INTEGER,
    seller_id INTEGER,
    rating INTEGER CHECK (rating BETWEEN 1 AND 5),
    comments VARCHAR(200),
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_seller FOREIGN KEY (seller_id) REFERENCES seller(seller_id)
);
