CREATE TABLE bank_account (
    bank_account_id SERIAL PRIMARY KEY,
    number_account VARCHAR(50) NOT NULL,
    agency VARCHAR(200) NOT NULL
);
