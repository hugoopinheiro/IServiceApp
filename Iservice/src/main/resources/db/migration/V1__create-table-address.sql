CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    street VARCHAR(200) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    complement VARCHAR(200),
    state VARCHAR(50) NOT NULL,
    house_number VARCHAR(10) NOT NULL
);
