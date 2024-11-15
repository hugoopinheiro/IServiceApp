CREATE TABLE credit_card (
    credit_card_id SERIAL PRIMARY KEY,
    expiration_date DATE NOT NULL,
    owner VARCHAR(200) NOT NULL,
    credit_card_number VARCHAR(255),
    user_id INTEGER,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
