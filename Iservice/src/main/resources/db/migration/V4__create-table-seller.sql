CREATE TABLE seller (
    seller_id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    bank_account_id INTEGER,
    user_id INTEGER NOT NULL,
    description VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bank_account FOREIGN KEY (bank_account_id) REFERENCES bank_account(bank_account_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);
