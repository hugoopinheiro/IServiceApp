CREATE TABLE bank_account (
    bankAccount_id SERIAL PRIMARY KEY,
    seller_id INTEGER NOT NULL,
    number_account VARCHAR(50) NOT NULL,
    agency VARCHAR(200) NOT NULL,
    CONSTRAINT fk_seller FOREIGN KEY (seller_id) REFERENCES seller(seller_id)
);
